package com.jlgdev.ceres.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Collation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.jlgdev.ceres.models.Utils.SearchUtils;
import com.jlgdev.ceres.models.dataTransferObject.NoSQL.RecipeDTO;
import com.jlgdev.ceres.models.request.SearchForm;
import com.jlgdev.ceres.repositories.RecipeRepository;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Iterable<RecipeDTO> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Optional<RecipeDTO> getRecipeById(String id) {
        return recipeRepository.findById(id);
    }

    public Iterable<RecipeDTO> getRecipeByTitle(String title) {
        return recipeRepository.findByTitleEn(title);
    }

    public Iterable<RecipeDTO> getRecipeByTitleContaining(String title) {
        return recipeRepository.findByTitleEnContaining(title);
    }

    public RecipeDTO save(RecipeDTO Recipe) {
        return recipeRepository.save(Recipe);
    }

    public List<RecipeDTO> searchByForm(SearchForm searchForm) {
        List<Criteria> andCriteria = new ArrayList<>();

        System.out.println("Je proc ");
        String title = searchForm.getTitle();
        if (title != null && !title.isEmpty()) {
            andCriteria.add(Criteria.where("titleFr").regex(title, "i"));
            System.out.println("Je proc titre");
        }

        String ingredients = searchForm.getIngredients();
        if (ingredients != null && !ingredients.isEmpty()) {
            System.out.println("Je proc Ingredient");
            String[] ingList = ingredients.split(",");
            List<Criteria> regexCriteria = Arrays.stream(ingList)
                    .map(ing -> Criteria.where("ingredients.aliment.nameFr").regex(ing.trim(), "i"))
                    .collect(Collectors.toList());
            Criteria ingredientCriteria;
            if (searchForm.getAllIngredient().toLowerCase().equals("tous")) {
                ingredientCriteria = new Criteria().orOperator(regexCriteria.toArray(new Criteria[0]));
            } else {
                ingredientCriteria = new Criteria().andOperator(regexCriteria.toArray(new Criteria[0]));
            }
            andCriteria.add(ingredientCriteria);
        }

        if (searchForm.getPreparationTime() > -1) {
            System.out.println("Je proc prep");
            andCriteria.add(Criteria.where("preparationMinutes").gt(-1).lte(searchForm.getPreparationTime()));
        }

        if (searchForm.getCookingTime() > -1) {
            System.out.println("Je proc cuis");
            andCriteria.add(Criteria.where("cookingMinutes").gt(-1).lte(searchForm.getCookingTime()));
        }

        if (searchForm.getTotalTime() > -1) {
            System.out.println("Je proc total");
            andCriteria.add(Criteria.where("totalMinutes").gt(-1).lte(searchForm.getTotalTime()));
        }

        String withoutIngredient = searchForm.getWithoutIngredient();
        Set<String> restrictions = searchForm.translateRestrictions();
        String restrictionRadio = searchForm.getRestrictionRadio();
        if (restrictionRadio != null
                && !restrictionRadio.isEmpty()
                && restrictionRadio.toLowerCase().equals("filter")) {
            System.out.println("Je proc bouton Filtrer");
            if (withoutIngredient != null && !withoutIngredient.isEmpty()) {
                System.out.println("Je proc sans ingrédients 1");
                String[] withoutList = withoutIngredient.split(",");
                System.out.println("withoutList = " + Arrays.toString(withoutList));
                List<Criteria> regexExclusion = Arrays.stream(withoutList)
                        .map(ingr -> {
                            System.out.println("Mot exclu regex = " + ingr);
                            return Criteria.where("ingredients.aliment.nameFr").regex(ingr.trim(), "i"); })
                        .collect(Collectors.toList());

                Criteria exclusionCriteria = new Criteria().norOperator(regexExclusion.toArray(new Criteria[0]));
                andCriteria.add(exclusionCriteria);
            }
            if (!restrictions.isEmpty()) {
                System.out.println("Je proc restriction 1");
                for (String allergy : restrictions) {
                    andCriteria.add(Criteria.where(allergy).ne(0));
                }
            }
        }

        Query query = new Query();
        if (!andCriteria.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(andCriteria.toArray(new Criteria[0])));
        }

        // Gestion du tri
        if (searchForm.getSortBy() != null && !searchForm.getSortBy().isEmpty()) {
            query.with(Sort.by(Sort.Direction.ASC, searchForm.getSortBy()));
        }

        // Collation pour ignorer accents et casse
        query.collation(Collation.of("fr").strength(Collation.ComparisonLevel.primary()));

        System.out.println("Query = " + query.toString());

        List<RecipeDTO> result = mongoTemplate.find(query, RecipeDTO.class);

        System.out.println("Nombre de résultats = " + result.size());
        // Si le bouton Avertir ou Filtrer a été mis sur Avertir
        if (restrictionRadio != null
                && !restrictionRadio.isEmpty()
                && restrictionRadio.toLowerCase().equals("warning")
                && ((withoutIngredient != null && !withoutIngredient.isEmpty())
                        || (restrictions != null && !restrictions.isEmpty()))) {

            System.out.println("Je proc bouton AVERTIR");
            // S'il y a des ingredients à éviter dans le formulaire alors on les mets dans
            // un Set
            Set<String> withoutSet = new HashSet<>();
            if (withoutIngredient != null && !withoutIngredient.isEmpty()) {
                withoutSet = new HashSet<>(Arrays.asList(withoutIngredient.split(",")));
                System.out.println("Je proc sans ingrédient 2");
            }

            // Pour chaque recette du résultat mon note les aliments
            for (RecipeDTO recipe : result) {
                byte consumable = 1;
                Set<String> alimentsNamesInRecipe = recipe.getRecipeAlimentsNames();

                // On vérifie si un des aliments à éviter est présent dans la recette
                if (!withoutSet.isEmpty()) {
                    for (String without : withoutSet) {
                        if (SearchUtils.isInIterable(alimentsNamesInRecipe, without)) {
                            recipe.setConsumable((byte) 0);
                            break;
                        }
                    }
                    // Si ce fut le cas on flag la recette et on passe directement à la suivante,
                    // pas besoin de vérifier s'il y a en plus des restrictions
                    if (recipe.getConsumable() == 0) {
                        continue;
                    }
                }

                // On récupère les restrictions de la recette
                Map<String, Byte> recipeRestrictionMap = recipe.getReducedRestrictionsMap();

                // S'il y a des restrictions dans le formulaire
                // ET qu'il y en a dans la recette
                // ET qu'il y en a en commun
                if (!restrictions.isEmpty()
                        && !recipeRestrictionMap.isEmpty()
                        && restrictions.stream().anyMatch(recipeRestrictionMap::containsKey)) {

                    // Alors on récupère les restrictions concernées
                    Set<String> commonKeys = restrictions.stream()
                            .filter(recipeRestrictionMap::containsKey)
                            .collect(Collectors.toSet());

                    // Si on a valider le coté commun, c'est qu'il y a au minimum une restriction
                    // partielle
                    consumable = 2;

                    // Les restrictions de recette n'ont que 0 ou 2 comme valeur (puisque 1 n'est
                    // pas une restriction)
                    for (String key : commonKeys) {
                        byte value = recipeRestrictionMap.get(key);
                        // Si la restriction est totale ( 0 ) alors on flag la recette et pas besoin de
                        // voir les autres aliments
                        if (value == 0) {
                            consumable = 0;
                            break;
                        }
                    }
                    recipe.setConsumable(consumable);
                }
            }

        } else {
            System.out.println("Je proc et tout va bien");
            for (RecipeDTO recipe : result) {
                recipe.setConsumable((byte) 1);
            }
        }
        return result;
    }
}