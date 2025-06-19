package com.jlgdev.ceres.controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import com.deepl.api.DeepLClient;
import com.deepl.api.DeepLException;
import com.deepl.api.TextResult;
import com.jlgdev.ceres.models.dataAccessObject.AlimentDAO;
import com.jlgdev.ceres.models.dataAccessObject.RecipeDAO;
import com.jlgdev.ceres.models.mapper.MapperRecipe;
import com.jlgdev.ceres.models.translation.TranslationQuery;
import com.jlgdev.ceres.services.AlimentService;
import com.jlgdev.ceres.services.RecipeService;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/translate")
@CrossOrigin(origins = "*")
// @Transactional
public class TranslationController {

    @Autowired
    private AlimentService alimentService;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    public MapperRecipe mapperRecipe;

    DeepLClient deeplClient;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/aliments/names")
    public String getAlimentsNames() {

        Iterable<AlimentDAO> allAliments = alimentService.getAllAliments();
        RestClient defaultClient = RestClient.create();
        String nameEn = "";
        String nameFr = "";

        for (AlimentDAO aliment : allAliments) {
            nameEn = aliment.getNameEn();

            TranslationQuery query = new TranslationQuery(nameEn);

            String response = defaultClient.post()
                    .uri("http://localhost:5000/translate")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(query)
                    .retrieve()
                    .body(String.class);

            if (response != null) {
                nameFr = response.indexOf("\"alternatives\":[]") != -1
                        ? response.substring(37, response.indexOf("}") - 1)
                        : response.substring(response.indexOf("[") + 2, response.indexOf("]") - 1);
                aliment.setNameFr(nameFr);
                alimentService.save(aliment);
            }
        }

        return nameFr;
    }

    @GetMapping("/recipes/titles")
    public String getRecipesTitles() throws DeepLException, InterruptedException {

        Iterable<RecipeDAO> allRecipes = recipeService.getAllRecipes();
        String titleEn = "";
        String titleFr = "";
        String allTitlesFr = "";
        String authKey = "10107230-e20d-40f8-afd1-fb61efbe1607:fx";
        deeplClient = new DeepLClient(authKey);
        int slowdown = 0;

        for (RecipeDAO recipe : allRecipes) {
            titleEn = recipe.getTitleEn();

            TextResult result = deeplClient.translateText(titleEn, "en", "fr");

            if (result != null) {
                titleFr = result.getText();
                System.out.println(titleFr);
                recipe.setTitleFr(titleFr);
                recipeService.save(recipe);
                allTitlesFr += titleFr + "\n";
            }
            if (++slowdown == 5) {
                Thread.sleep(1000);
                slowdown = 0;
            }
        }

        return allTitlesFr;
    }

    @GetMapping("/aliments/categories")
    public String getAlimentsCategories() throws DeepLException, InterruptedException, IOException {

        Iterable<AlimentDAO> allAliments = alimentService.getAllAliments();
        Set<String> categoriesAliment = new HashSet<>();
        String authKey = "10107230-e20d-40f8-afd1-fb61efbe1607:fx";
        deeplClient = new DeepLClient(authKey);

        File myFile = new File(
                "C:\\Users\\Elvariand\\Coding\\Projets\\Ceres\\Code\\ceres\\src\\main\\java\\com\\jlgdev\\ceres\\models\\translation\\categories.txt");
        Scanner myReader = new Scanner(myFile);
        String strFile = "";
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            strFile += data + "\n";
        }
        myReader.close();
        String[] arrayFile = strFile.split("\n");
        List<String> listAllCategoriesEn = new ArrayList<>();
        List<String> listAllCategoriesFr = new ArrayList<>();

        for (String string : arrayFile) {
            String[] cat = string.split(" -> ");
            listAllCategoriesEn.add(cat[0]);
            listAllCategoriesFr.add(cat[1]);
        }

        for (AlimentDAO aliment : allAliments) {
            categoriesAliment = aliment.getCategoryPath();

            if (categoriesAliment.size() > 0) {

                int index = 0;
                Set<String> categoriesAlimentFr = new HashSet<>();

                for (String catEn : categoriesAliment) {
                    index = listAllCategoriesEn.indexOf(catEn);
                    categoriesAlimentFr.add(listAllCategoriesFr.get(index));
                }

                aliment.setCategoryPathFr(categoriesAlimentFr);
                alimentService.save(aliment);
                System.out.println(categoriesAlimentFr);
            }
        }

        return "done";
    }

    @GetMapping("/correction")
    public void correction() {
        Iterable<AlimentDAO> allAliments = alimentService.getAllAliments();

        for (AlimentDAO aliment : allAliments) {
            Set<String> categories = aliment.getCategoryPathFr();
            if (categories != null && categories.contains("boire")) {
                categories.remove("boire");
                categories.add("boisson");
                alimentService.save(aliment);
            }
        }
    }

    @GetMapping("/correctionPath")
    public void correctionMissingPath() throws DeepLException, InterruptedException {
        Iterable<AlimentDAO> allAliments = alimentService.getAlimentMissingPath();
        String authKey = "10107230-e20d-40f8-afd1-fb61efbe1607:fx";
        deeplClient = new DeepLClient(authKey);
        int slowdown = 0;
        for (AlimentDAO aliment : allAliments) {
            Set<String> pathFr = new HashSet<>();
            for (String pathEn : aliment.getCategoryPathEn()) {
                

                TextResult result = deeplClient.translateText(pathEn, "en", "fr");

                if (result != null) {
                    pathFr.add(result.getText());
                    aliment.setCategoryPathFr(pathFr);
                    alimentService.save(aliment);
                }
                if (++slowdown == 10) {
                    Thread.sleep(1000);
                    slowdown = 0;
                }
            }
        }
    }
}
