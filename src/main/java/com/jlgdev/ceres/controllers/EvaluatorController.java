package com.jlgdev.ceres.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jlgdev.ceres.models.dataTransferObject.AlimentDTO;
import com.jlgdev.ceres.models.dataTransferObject.AllergyDTO;
import com.jlgdev.ceres.models.dataTransferObject.IngredientDTO;
import com.jlgdev.ceres.models.dataTransferObject.RecipeDTO;
import com.jlgdev.ceres.models.mapper.MapperRecipe;
import com.jlgdev.ceres.services.AlimentService;
import com.jlgdev.ceres.services.AllergyService;
import com.jlgdev.ceres.services.RecipeService;

@RestController
@RequestMapping("/evaluate")
@CrossOrigin(origins = "*")
public class EvaluatorController {

    @Autowired
    private AlimentService alimentService;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private AllergyService allergyService;

    @Autowired
    public MapperRecipe mapperRecipe;

    Logger log = LoggerFactory.getLogger(this.getClass());

/**************************************************
 *             Recipe evaluation                  *
 **************************************************/

    @GetMapping("/recipes/juge")
    public String determineRecipeRestrictions() {

        Iterable<RecipeDTO> allRecipes = recipeService.getAllRecipes();

        for (RecipeDTO recipe : allRecipes) {
            evaluateAllergies(recipe);
            evaluateRecipeVeganism(recipe);
            evaluateRecipeReligion(recipe);
        }
        return "Recipes have been evaluated";
    }

    private RecipeDTO evaluateAllergies(RecipeDTO recipe) {

        Iterable<AllergyDTO> allAllergies = allergyService.getAllAllergies();
        Set<AlimentDTO> recipeAliments = getAlimentsFromRecipe(recipe);

        for (AllergyDTO allergy : allAllergies) {
            byte restriction = RecipeDTO.OK;
            for (AlimentDTO aliment : recipeAliments) {
                String alimentNameFr = aliment.getNameFr();
                if (isInIterable(allergy.getForbidden(), alimentNameFr)) {
                    restriction = RecipeDTO.FORBIDDEN;
                    break;
                } else if (isInIterable(allergy.getWarning(), alimentNameFr)) {
                    restriction = RecipeDTO.WARNING;
                }
            }
            switch (allergy.getName()) {
                case "arachid":
                    recipe.setArachidfree(restriction);
                    break;
                case "celery":
                    recipe.setCeleryfree(restriction);
                    break;
                case "dairy":
                    recipe.setDairyfree(restriction);
                    break;
                case "egg":
                    recipe.setEggfree(restriction);
                    break;
                case "fish":
                    recipe.setFishfree(restriction);
                    break;
                case "gluten":
                    recipe.setGlutenfree(restriction);
                    break;
                case "lupine":
                    recipe.setLupinefree(restriction);
                    break;
                case "mustard":
                    recipe.setMustardfree(restriction);
                    break;
                case "nut":
                    recipe.setNutfree(restriction);
                    break;
                case "seefood":
                    recipe.setSeefoodfree(restriction);
                    break;
                case "sesame":
                    recipe.setSesamefree(restriction);
                    break;
                case "shellfish":
                    recipe.setShellfishfree(restriction);
                    break;
                case "soy":
                    recipe.setSoyfree(restriction);
                    break;
                case "sulfit":
                    recipe.setSulfitfree(restriction);
                    break;

                default:
                    System.err.println("Une erreur a eu lieu ici (allergie recette) : " + recipe.getId());
                    break;
            }
        }
        recipeService.save(recipe);

        return recipe;
    }

    private RecipeDTO evaluateRecipeReligion(RecipeDTO recipe) {

        recipe.setJudaism(RecipeDTO.OK);
        recipe.setIslam(RecipeDTO.OK);
        if (recipe.getVegan() == 1) {
            recipeService.save(recipe);
        } else {
            Set<AlimentDTO> recipeAliments = getAlimentsFromRecipe(recipe);
            Set<AlimentDTO> recipeJudaismWarnings = new HashSet<>();

            for (AlimentDTO aliment : recipeAliments) {
                if (aliment.getJudaism() == AlimentDTO.FORBIDDEN) {
                    recipe.setJudaism(RecipeDTO.FORBIDDEN);
                }
                if (aliment.getIslam() == AlimentDTO.FORBIDDEN) {
                    recipe.setIslam(RecipeDTO.FORBIDDEN);
                }
                if (recipe.getJudaism() != RecipeDTO.FORBIDDEN
                        && aliment.getJudaism() == AlimentDTO.WARNING
                        && aliment.getVegan() != AlimentDTO.OK) {
                    recipeJudaismWarnings.add(aliment);
                }
            }
            if (recipeJudaismWarnings.size() > 1) {
                boolean hasMeat = false;
                boolean hasMilk = false;
                boolean hasFish = false;

                for (AlimentDTO warning : recipeJudaismWarnings) {
                    Set<String> paths = warning.getCategoryPathFr();
                    if (paths == null) {
                        System.out.println(warning.getId() + " n'a pas de path");
                        recipe.setJudaism(RecipeDTO.WARNING);
                    } else {

                        if (paths.contains("viande")) {
                            hasMeat = true;
                        } else if (paths.contains("poisson")) {
                            hasFish = true;
                        } else if (paths.contains("lait")
                                || paths.contains("beurre")
                                || paths.contains("yaourt")
                                || paths.contains("yoghourt")
                                || paths.contains("fromage")) {
                            hasMilk = true;
                        }
                    }
                }
                if (hasMeat && hasMilk) {
                    recipe.setJudaism(RecipeDTO.FORBIDDEN);
                } else if (hasFish && hasMeat) {
                    recipe.setJudaism(RecipeDTO.WARNING);
                }
            }
            recipeService.save(recipe);

        }
        return recipe;
    }

    private RecipeDTO evaluateRecipeVeganism(RecipeDTO recipe) {

        Set<AlimentDTO> recipeAliments = getAlimentsFromRecipe(recipe);
        byte isVegan = RecipeDTO.OK;
        byte isVegetarian = RecipeDTO.OK;

        for (AlimentDTO aliment : recipeAliments) {
            if (aliment.getVegetarian() == AlimentDTO.FORBIDDEN) {
                isVegan = RecipeDTO.FORBIDDEN;
                isVegetarian = RecipeDTO.FORBIDDEN;
                break;
            } else if (aliment.getVegan() == AlimentDTO.FORBIDDEN) {
                isVegan = RecipeDTO.FORBIDDEN;
            }
        }

        recipe.setVegan(isVegan);
        recipe.setVegetarian(isVegetarian);
        recipeService.save(recipe);

        return recipe;
    }

/**************************************************
 *             Aliment evaluation                 *
 **************************************************/

    @GetMapping("/aliments/juge")
    public String determineRestrictions() {

        Iterable<AlimentDTO> allAliments = alimentService.getAllAliments();

        for (AlimentDTO aliment : allAliments) {
            evaluateAllergies(aliment);
            evaluateVeganism(aliment);
            evaluateReligion(aliment);
        }
        return "Aliments have been evaluated";
    }

    private AlimentDTO evaluateAllergies(AlimentDTO aliment) {

        Iterable<AllergyDTO> allAllergies = allergyService.getAllAllergies();

        for (AllergyDTO allergy : allAllergies) {
            byte restriction = AlimentDTO.OK;
            String alimentNameFr = aliment.getNameFr();

            if (isInIterable(allergy.getForbidden(), alimentNameFr)) {
                restriction = AlimentDTO.FORBIDDEN;
            } else if (isInIterable(allergy.getWarning(), alimentNameFr)) {
                restriction = AlimentDTO.WARNING;
            }

            switch (allergy.getName()) {
                case "arachid":
                    aliment.setArachidfree(restriction);
                    break;
                case "celery":
                    aliment.setCeleryfree(restriction);
                    break;
                case "dairy":
                    aliment.setDairyfree(restriction);
                    break;
                case "egg":
                    aliment.setEggfree(restriction);
                    break;
                case "fish":
                    aliment.setFishfree(restriction);
                    break;
                case "gluten":
                    aliment.setGlutenfree(restriction);
                    break;
                case "lupine":
                    aliment.setLupinefree(restriction);
                    break;
                case "mustard":
                    aliment.setMustardfree(restriction);
                    break;
                case "nut":
                    aliment.setNutfree(restriction);
                    break;
                case "seefood":
                    aliment.setSeefoodfree(restriction);
                    break;
                case "sesame":
                    aliment.setSesamefree(restriction);
                    break;
                case "shellfish":
                    aliment.setShellfishfree(restriction);
                    break;
                case "soy":
                    aliment.setSoyfree(restriction);
                    break;
                case "sulfit":
                    aliment.setSulfitfree(restriction);
                    break;

                default:
                    System.err.println("Une erreur a eu lieu ici (allergie aliment) : " + aliment.getId());
                    break;
            }
        }
        alimentService.save(aliment);

        return aliment;
    }

    private AlimentDTO evaluateVeganism(AlimentDTO aliment) {

        Set<String> categoriesPathFr = aliment.getCategoryPathFr();
        byte vegan = AlimentDTO.OK;
        byte vegetarian = AlimentDTO.OK;
        String alimentNameFr = aliment.getNameFr();

        if (categoriesPathFr == null) {
            categoriesPathFr = new HashSet<>();
        }
        categoriesPathFr.add(alimentNameFr);

        Set<String> nonVegan = new HashSet<>();
        nonVegan.add("oeuf");
        nonVegan.add("œuf");
        nonVegan.add("lait");
        nonVegan.add("fromage");
        nonVegan.add("miel");
        nonVegan.add("beurre");
        nonVegan.add("crème");
        nonVegan.add("yaourt");
        nonVegan.add("yogourt");

        Set<String> veganMilk = new HashSet<>();
        veganMilk.add("amande");
        veganMilk.add("épeautre");
        veganMilk.add("epeautre");
        veganMilk.add("noisette");
        veganMilk.add("soja");
        veganMilk.add("riz");
        veganMilk.add("noix");
        veganMilk.add("cajou");
        veganMilk.add("coco");
        veganMilk.add("chanvre");
        veganMilk.add("végétal");
        veganMilk.add("vegetal");
        veganMilk.add("substitut de lait");

        Set<String> warningVegan = new HashSet<>();
        nonVegan.add("bonbon");

        Set<String> nonVegetarian = new HashSet<>();
        nonVegetarian.add("poisson");
        nonVegetarian.add("crustacé");
        nonVegetarian.add("crustace");
        nonVegetarian.add("coquillage");
        nonVegetarian.add("viande");
        nonVegetarian.add("porc");
        nonVegetarian.add("boeuf");
        nonVegetarian.add("bœuf");
        nonVegetarian.add("poulet");
        nonVegetarian.add("dinde");
        nonVegetarian.add("mouton");
        nonVegetarian.add("brebis");
        nonVegetarian.add("cochon");
        nonVegetarian.add("lard");
        nonVegetarian.add("volaille");
        nonVegetarian.add("charcuterie");

        for (String cat : categoriesPathFr) {

            if (isInIterable(nonVegetarian, cat)) {
                vegan = AlimentDTO.FORBIDDEN;
                vegetarian = AlimentDTO.FORBIDDEN;
                break;
            }

            if (isInIterable(nonVegan, cat)) {
                vegan = AlimentDTO.FORBIDDEN;
                if (cat.contains("lait") || cat.contains("yaourt") || cat.contains("yogourt")) {
                    if (isInIterable(veganMilk, cat)) {
                        vegan = AlimentDTO.OK;
                        break;
                    }
                } else if (cat.contains("beurre") && !cat.contains("noix de beurre")) {
                    if (cat.contains("végétal")
                            || cat.contains("vegetal")
                            || cat.contains("de pomme")
                            || cat.contains("vegan")) {
                        vegan = AlimentDTO.OK;
                        break;
                    }
                }
            } else if (isInIterable(warningVegan, cat)) {
                vegan = AlimentDTO.WARNING;
            }
        }

        aliment.setVegan(vegan);
        aliment.setVegetarian(vegetarian);

        alimentService.save(aliment);

        return aliment;
    }

    private AlimentDTO evaluateReligion(AlimentDTO aliment) {

        if (aliment.getVegan() == 1) {
            aliment.setJudaism(AlimentDTO.OK);
            aliment.setIslam(AlimentDTO.OK);
            alimentService.save(aliment);
            return aliment;
        } else {
            Iterable<AllergyDTO> allReligions = allergyService.getReligions();

            for (AllergyDTO religion : allReligions) {
                byte restriction = AlimentDTO.OK;
                Set<String> categories = aliment.getCategoryPathFr();

                for (String cat : categories) {
                    if (isInIterable(religion.getForbidden(), cat)) {
                        restriction = AlimentDTO.FORBIDDEN;
                    } else if (isInIterable(religion.getWarning(), cat)) {
                        restriction = AlimentDTO.WARNING;
                    }
                }

                switch (religion.getName()) {
                    case "judaism":
                        aliment.setJudaism(restriction);
                        break;
                    case "islam":
                        aliment.setIslam(restriction);
                        break;
                    default:
                        System.err.println("Une erreur a eu lieu ici (religion aliment) : " + aliment.getId());
                        break;
                }
            }
            alimentService.save(aliment);

            return aliment;

        }
    }

/**************************************************
 *              Common methods                    *
 **************************************************/

    private Set<AlimentDTO> getAlimentsFromRecipe(RecipeDTO recipe) {
        Set<AlimentDTO> recipeAliments = new HashSet<>();
        List<IngredientDTO> recipeIngredients = recipe.getIngredients();

        for (IngredientDTO ingredient : recipeIngredients) {
            recipeAliments.add(ingredient.getAliment());
        }

        return recipeAliments;
    }

    private boolean isInIterable(Iterable<String> haystack, String needle) {
        if (haystack == null || needle == null) {
            return false;
        }
        for (String hay : haystack) {

            Pattern pattern = Pattern.compile("(.*(\\s|\'))?\\b" + hay + "(s|x)?\\b(\\s.*)?");
            Matcher matcher = pattern.matcher(needle);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }
}
