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
import com.jlgdev.ceres.models.dataAccessObject.AlimentDAO;
import com.jlgdev.ceres.models.dataAccessObject.AllergyDAO;
import com.jlgdev.ceres.models.dataAccessObject.IngredientDAO;
import com.jlgdev.ceres.models.dataAccessObject.RecipeDAO;
import com.jlgdev.ceres.models.mapper.MapperRecipe;
import com.jlgdev.ceres.services.AlimentService;
import com.jlgdev.ceres.services.AllergyService;
import com.jlgdev.ceres.services.RecipeService;

@RestController
@RequestMapping("/evaluate")
@CrossOrigin(origins = "*")
// @Transactional
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

    @GetMapping("/recipes/juge")
    public String determineRestrictions() {

        Iterable<RecipeDAO> allRecipes = recipeService.getAllRecipes();
        int count = 0;

        for (RecipeDAO recipe : allRecipes) {
            evaluateAllergies(recipe);
            evaluateRecipeVeganism(recipe);
            evaluateRecipeReligion(recipe);
            if (++count > 1300) {
                break;
            }
        }
        return "you've been served";
    }

    private Set<AlimentDAO> getAlimentsFromRecipe(RecipeDAO recipe) {
        Set<AlimentDAO> recipeAliments = new HashSet<>();
        List<IngredientDAO> recipeIngredients = recipe.getIngredients();

        for (IngredientDAO ingredient : recipeIngredients) {
            recipeAliments.add(ingredient.getAliment());
        }

        return recipeAliments;
    }

    private RecipeDAO evaluateAllergies(RecipeDAO recipe) {

        Iterable<AllergyDAO> allAllergies = allergyService.getAllAllergies();
        Set<AlimentDAO> recipeAliments = getAlimentsFromRecipe(recipe);

        for (AllergyDAO allergy : allAllergies) {
            byte restriction = RecipeDAO.OK;
            for (AlimentDAO aliment : recipeAliments) {
                String alimentNameFr = aliment.getNameFr();
                if (isInIterable(allergy.getForbidden(), alimentNameFr)) {
                    System.out.println(allergy.getName());
                    restriction = RecipeDAO.FORBIDDEN;
                    break;
                } else if (isInIterable(allergy.getWarning(), alimentNameFr)) {
                    System.out.println("warning " + allergy.getName());
                    restriction = RecipeDAO.WARNING;
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
                    System.err.println("Une erreur a eu lieu ici");
                    break;
            }
        }
        recipeService.save(recipe);

        return recipe;
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

    @GetMapping("/aliments/juge")
    public String determineAlimentRestrictions() {

        Iterable<AlimentDAO> allAliments = alimentService.getAllAliments();
        int count = 0;

        for (AlimentDAO aliment : allAliments) {
            evaluateAllergies(aliment);
            evaluateVeganism(aliment);
            evaluateReligion(aliment);
            if (++count > 1300) {
                break;
            }
        }
        return "Aliments all got evaluated";
    }

    private AlimentDAO evaluateAllergies(AlimentDAO aliment) {

        Iterable<AllergyDAO> allAllergies = allergyService.getAllAllergies();

        for (AllergyDAO allergy : allAllergies) {
            byte restriction = AlimentDAO.OK;
            String alimentNameFr = aliment.getNameFr();

            if (isInIterable(allergy.getForbidden(), alimentNameFr)) {
                restriction = AlimentDAO.FORBIDDEN;
            } else if (isInIterable(allergy.getWarning(), alimentNameFr)) {
                System.out.println("warning " + allergy.getName());
                restriction = AlimentDAO.WARNING;
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
                    System.err.println("Une erreur a eu lieu ici");
                    break;
            }
        }
        alimentService.save(aliment);

        return aliment;
    }

    private AlimentDAO evaluateVeganism(AlimentDAO aliment) {

        Set<String> categoriesPathFr = aliment.getCategoryPathFr();
        byte vegan = AlimentDAO.OK;
        byte vegetarian = AlimentDAO.OK;
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

        Set<String> veganMilk = new HashSet<>();
        nonVegan.add("amande");
        nonVegan.add("épeautre");
        nonVegan.add("epeautre");
        nonVegan.add("noisette");
        nonVegan.add("soja");
        nonVegan.add("riz");
        nonVegan.add("noix");
        nonVegan.add("cajou");
        nonVegan.add("coco");
        nonVegan.add("végétal");
        nonVegan.add("vegetal");

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
                System.out.println(aliment.getId() + " = " + alimentNameFr);
                vegan = AlimentDAO.FORBIDDEN;
                vegetarian = AlimentDAO.FORBIDDEN;
                break;
            }

            if (isInIterable(nonVegan, cat)) {
                vegan = AlimentDAO.FORBIDDEN;
                if (cat.contains("lait")) {
                    if (isInIterable(veganMilk, cat)) {
                        vegan = AlimentDAO.OK;
                    }
                } else if (cat.contains("beurre") && !cat.contains("noix de beurre")) {
                    if (cat.contains("végétal")
                    || cat.contains("vegetal")
                    || cat.contains("de pomme")
                    || cat.contains("vegan") ) {
                        vegan = AlimentDAO.OK;
                    }
                }
            } else if (isInIterable(warningVegan, cat)) {
                vegan = AlimentDAO.WARNING;
            }
        }

        aliment.setVegan(vegan);
        aliment.setVegetarian(vegetarian);

        alimentService.save(aliment);

        return aliment;
    }


    private RecipeDAO evaluateRecipeVeganism(RecipeDAO recipe) {

        Set<AlimentDAO> recipeAliments = getAlimentsFromRecipe(recipe);
        byte isVegan = RecipeDAO.OK;
        byte isVegetarian = RecipeDAO.OK;

        for (AlimentDAO aliment : recipeAliments) {
            if (aliment.getVegetarian() == AlimentDAO.FORBIDDEN) {
                isVegan = RecipeDAO.FORBIDDEN;
                isVegetarian = RecipeDAO.FORBIDDEN;
                break;
            } else if (aliment.getVegan() == AlimentDAO.FORBIDDEN) {
                isVegan = RecipeDAO.FORBIDDEN;
            }
        }

        recipe.setVegan(isVegan);
        recipe.setVegetarian(isVegetarian);
        recipeService.save(recipe);

        return recipe;
    }

    private AlimentDAO evaluateReligion(AlimentDAO aliment) {

        if (aliment.getVegan() == 1) {
            aliment.setJudaism(AlimentDAO.OK);
            aliment.setIslam(AlimentDAO.OK);
            alimentService.save(aliment);
            return aliment;
        } else {
            Iterable<AllergyDAO> allReligions = allergyService.getReligions();

            for (AllergyDAO religion : allReligions) {
                byte restriction = AlimentDAO.OK;
                String alimentNameFr = aliment.getNameFr();

                if (isInIterable(religion.getForbidden(), alimentNameFr)) {
                    restriction = AlimentDAO.FORBIDDEN;
                } else if (isInIterable(religion.getWarning(), alimentNameFr)) {
                    restriction = AlimentDAO.WARNING;
                }

                switch (religion.getName()) {
                    case "judaism":
                        aliment.setJudaism(restriction);
                        break;
                    case "islam":
                        aliment.setIslam(restriction);
                        break;
                    default:
                        System.err.println("Un erreur a eu lieu ici");
                        break;
                }
            }
            alimentService.save(aliment);

            return aliment;

        }
    }


    private RecipeDAO evaluateRecipeReligion(RecipeDAO recipe) {

        recipe.setJudaism(RecipeDAO.OK);
        recipe.setIslam(RecipeDAO.OK);
        if (recipe.getVegan() == 1) {
            recipeService.save(recipe);
        } else {
            Set<AlimentDAO> recipeAliments = getAlimentsFromRecipe(recipe);
            Set<AlimentDAO> recipeJudaismWarnings = new HashSet<>();

            for (AlimentDAO aliment : recipeAliments) {
                if (aliment.getJudaism() == AlimentDAO.FORBIDDEN) {
                    recipe.setJudaism(RecipeDAO.FORBIDDEN);
                }
                if (aliment.getIslam() == AlimentDAO.FORBIDDEN) {
                    recipe.setIslam(RecipeDAO.FORBIDDEN);
                }
                if (recipe.getJudaism() != RecipeDAO.FORBIDDEN
                        && aliment.getJudaism() == AlimentDAO.WARNING
                        && aliment.getVegan() != AlimentDAO.OK) {
                    recipeJudaismWarnings.add(aliment);
                }
            }
            if (recipeJudaismWarnings.size() > 1) {
                boolean hasMeat = false;
                boolean hasMilk = false;
                boolean hasFish = false;

                for (AlimentDAO warning : recipeJudaismWarnings) {
                    Set<String> paths = warning.getCategoryPathFr();
                    if (paths == null) {
                        System.out.println(warning.getId() + " n'a pas de path");
                        recipe.setJudaism(RecipeDAO.WARNING);
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
                    recipe.setJudaism(RecipeDAO.FORBIDDEN);
                } else if (hasFish && hasMeat) {
                    recipe.setJudaism(RecipeDAO.WARNING);
                }
            }
            recipeService.save(recipe);

        }
        return recipe;
    }
}
