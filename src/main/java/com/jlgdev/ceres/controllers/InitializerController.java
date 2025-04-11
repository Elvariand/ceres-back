package com.jlgdev.ceres.controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jlgdev.ceres.models.jsonToObject.AlimentJTO;
import com.jlgdev.ceres.models.dataAccessObject.AlimentDAO;
import com.jlgdev.ceres.models.dataAccessObject.IngredientDAO;
import com.jlgdev.ceres.models.dataAccessObject.MissingIngredients;
import com.jlgdev.ceres.models.dataAccessObject.RecipeDAO;
import com.jlgdev.ceres.models.jsonToObject.RecipeJTO;
import com.jlgdev.ceres.models.jsonToObject.RecipeTransferJTO;
import com.jlgdev.ceres.models.mapper.MapperAliment;
import com.jlgdev.ceres.models.mapper.MapperRecipe;
import com.jlgdev.ceres.services.AlimentService;
import com.jlgdev.ceres.services.MissingService;
import com.jlgdev.ceres.services.RecipeService;

@RestController
@RequestMapping("/initialize")
@CrossOrigin(origins = "*")
// @Transactional
public class InitializerController {

    @Autowired
    private AlimentService alimentService;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private MissingService missingService;

    @Autowired
    public MapperRecipe mapperRecipe;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/list")
    public @ResponseBody Iterable<AlimentDAO> getAllAliments() {
        log.info("\n---------------------------- Appel à getAllAliments()\n");
        return alimentService.getAllAliments();
    }

    @GetMapping("aliment/add/{id}")
    public ResponseEntity<AlimentDAO> addAliment(@PathVariable String id) {
        try {
            RestClient defaultClient = RestClient.create();

            String response = defaultClient.get().uri(
                    "https://api.spoonacular.com/food/ingredients/{id}/information?apiKey=6db25c150fbc44eebca76e569f90defc&amount=100&unit=grams&locale=en_US",
                    id)
                    .retrieve()
                    .body(String.class);

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            AlimentDAO alimentDAO = new AlimentDAO();
            AlimentJTO alimentJTO = new AlimentJTO();
            try {
                alimentJTO = mapper.readValue(response, AlimentJTO.class);
                alimentDAO = alimentService.save(MapperAliment.mapAliment(alimentJTO));
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            if (!alimentDAO.equals(new AlimentDAO())) {
                return new ResponseEntity<AlimentDAO>(alimentDAO, HttpStatus.OK);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("aliment/addBulkFrom/{index}")
    public ResponseEntity<AlimentDAO> addMultipleAliments(@PathVariable int index) throws InterruptedException {
        boolean canContinue = true;
        int[] idsFromCsv = { 1002002, 11482, 6979, 19912, 15117, 93606, 1002050, 93740, 93607, 12061, 10014534,
                10211962, 15001, 7064, 18087, 10020420, 93653, 9003, 19294, 1009016, 9016, 1042035, 19719, 9021,
                10020052, 20003, 93828, 11007, 11959, 1032035, 0, 1001033, 9252, 11011, 9037, 4581, 10311821, 93636,
                11960, 10011168, 11457, 10010204, 10192, 10123, 4609, 18033, 19078, 18371, 18372, 98998, 2069, 11028,
                9040, 10020444, 2004, 6150, 16069, 23572, 13023, 6008, 13786, 6170, 13926, 14003, 14006, 11080,
                10211821, 1009054, 18010, 18009, 19903, 99210, 16015, 1059195, 1002030, 10012023, 9042, 12062, 1009200,
                1004, 9050, 11116, 1055062, 10014037, 10114037, 18064, 10120129, 7919, 1006, 11090, 10011090, 20040,
                20090, 19334, 18632, 11098, 20012, 1001, 11250, 1230, 11485, 19070, 11109, 43015, 1032028, 10020129,
                93759, 98857, 93775, 16018, 11531, 16058, 11980, 16034, 11264, 16044, 10016034, 10011693, 10115121,
                16051, 10016051, 10716050, 9181, 2054, 19364, 19074, 2005, 2006, 1002006, 15008, 11124, 15010, 11135,
                10011135, 10111135, 11143, 10111143, 11141, 1052047, 2007, 8029, 10043155, 99236, 1009, 1041009, 98921,
                1188, 6038, 10093727, 9070, 19314, 10311529, 12098, 12006, 6080, 6480, 1006080, 5062, 6194, 5066, 5075,
                1005006, 93668, 6172, 1015062, 5091, 5100, 16057, 93749, 6973, 11962, 2009, 6972, 11632, 98839, 99223,
                93748, 19081, 28027, 10419903, 19270, 1102, 18166, 14181, 10118157, 7019, 2048, 11165, 99020, 1002010,
                10219335, 18047, 14187, 15157, 93632, 1002011, 1002047, 2035, 98846, 19165, 12104, 98929, 93746, 12115,
                1032050, 93747, 12118, 4047, 12119, 15015, 14209, 10414037, 14400, 1011, 11161, 6010, 6147, 99084,
                10862, 20041, 5064, 10802, 10220445, 20421, 1008166, 20137, 20088, 10118192, 2012, 11168, 18022, 19003,
                8020, 20019, 11172, 42289, 18363, 18023, 13346, 5307, 35137, 20027, 1001019, 1012, 10018029, 11477,
                20028, 16063, 10015136, 2030, 9078, 43382, 1053, 1017, 1186, 6016, 18373, 11174, 10116098, 1001056,
                11266, 1002031, 8066, 18242, 93751, 11206, 2014, 18139, 9085, 93604, 10016223, 10019334, 19904,
                10019904, 10019071, 1004058, 9087, 18945, 10010151, 7259, 8121, 10014057, 99186, 14146, 1002046, 2045,
                10011937, 21118, 1011053, 9032, 2003, 93822, 99233, 9079, 2017, 11284, 10011268, 1002038, 2042, 11955,
                18079, 1090, 1002024, 14097, 16090, 4574, 10019165, 11212, 1226, 20409, 1124, 1125, 1057, 11209,
                10120499, 6599, 10111205, 18439, 98887, 11213, 14210, 1214, 16163, 1034053, 10120420, 10020005, 42193,
                6984, 11957, 2018, 98963, 2019, 1019, 10020409, 98849, 10115261, 6179, 6963, 23657, 10012220, 1022047,
                20081, 10218364, 1020, 10711111, 98878, 1123, 18029, 2044, 11043, 11156, 11167, 10011167, 9089, 9431,
                10111297, 2064, 1026, 2063, 2049, 93709, 19230, 93629, 11913, 11463, 19100, 10019348, 19065, 93663,
                16157, 11215, 10111215, 1022020, 2020, 1062047, 19177, 99040, 10514037, 11216, 14136, 93754, 10093754,
                18172, 98853, 1159, 9297, 1011004, 1022, 10018617, 18942, 18617, 10020088, 10314534, 1089003, 8212,
                1002020, 10111529, 9112, 4517, 6997, 16025, 1256, 11052, 11333, 31015, 1441111, 1019132, 1029195, 11291,
                21052, 10093624, 1022034, 2001, 1022009, 10023572, 5332, 1052009, 1012010, 2010, 2011, 1002013, 1002014,
                12220, 2021, 17224, 2022, 2025, 10219, 7063, 17142, 1023, 1009037, 1049, 15036, 10151, 18350, 1129,
                1006972, 11390, 12120, 6987, 93602, 1012042, 1002044, 93743, 6175, 99227, 1002055, 6168, 16158,
                10014412, 19095, 19184, 14214, 10014214, 19332, 10118375, 93764, 10028033, 93651, 7036, 1022027, 99002,
                11979, 10120444, 19297, 11603, 93645, 1019016, 10520420, 93633, 93716, 1009195, 11233, 11935, 93768,
                9148, 1082047, 18423, 10017224, 10620420, 18133, 23557, 5662, 10060, 11246, 17013, 9150, 93834,
                12311111, 9152, 9156, 1012030, 1029150, 11972, 11252, 93623, 4602, 99009, 19350, 43274, 4641, 1054053,
                10216124, 9159, 9160, 1029159, 1009159, 10720420, 93627, 10811111, 14037, 15147, 10220444, 1088, 1082,
                1174, 1117, 1037, 1179, 6970, 16424, 1006970, 10093741, 10115136, 19157, 12131, 32004, 2015, 14311,
                9383, 9218, 9176, 19911, 9328, 4073, 10111549, 2023, 14057, 93644, 19116, 20317, 93820, 99144, 98932,
                4025, 1015006, 1065062, 10110219, 9421, 93772, 1009152, 1077, 10019146, 98991, 20048, 4014, 93830,
                16112, 19304, 1001025, 11260, 15164, 2046, 2024, 11119, 9202, 9191, 11352, 1011256, 1200, 1085, 11446,
                12195, 93620, 19125, 93690, 20132, 8120, 4582, 11956, 11278, 1052034, 4053, 9195, 11282, 2026, 6094,
                9200, 10011821, 9206, 9214, 10414534, 19303, 12511111, 9216, 2027, 10018166, 10920420, 6176, 15167,
                93831, 10410123, 98847, 10018079, 9226, 2028, 1033, 11297, 2029, 11298, 1028, 20420, 99036, 10011549,
                10020080, 9236, 16098, 93762, 19150, 4042, 16091, 98988, 20005, 10111282, 11304, 12142, 10012142, 1038,
                11120420, 11976, 1025, 1022030, 98858, 1022050, 7057, 10111333, 93698, 11944, 11937, 27027, 18334,
                1049195, 11943, 12147, 9266, 1029354, 9354, 9273, 1032047, 16043, 12151, 18413, 93770, 98924, 1001256,
                1118, 1001116, 9277, 9279, 10411529, 10011333, 10035137, 7059, 9442, 10042040, 9286, 19034, 2033,
                10010219, 16009, 10005, 10084, 10010062, 1007063, 10062, 10225, 10010225, 10072, 10218, 10114057, 11265,
                23612, 19411, 11413, 11362, 2034, 19336, 19047, 1253, 10010123, 1035, 9291, 18337, 11422, 11426,
                1002035, 11424, 12014, 1228, 8402, 20035, 93773, 11952, 11429, 9299, 10193663, 6583, 4639, 93733, 9302,
                10719297, 12087, 15152, 93721, 99229, 1079003, 11821, 11112, 11819, 1059003, 1451111, 98926, 9132,
                16033, 10016069, 10011282, 1032009, 2031, 10011355, 18157, 14096, 1022068, 1001168, 16202, 93618, 93610,
                18205, 9307, 98937, 20444, 20061, 8065, 93761, 20133, 10118368, 93784, 1022053, 43479, 1036, 18621,
                93713, 5114, 12135, 16092, 11916, 10211529, 10111251, 10011298, 2036, 11320420, 5348, 23617, 1012034,
                12211111, 19296, 11353, 11435, 18060, 98905, 2037, 2038, 99226, 4114, 7071, 15076, 6164, 27028, 2047,
                1102047, 1001001, 18228, 18353, 11439, 1017063, 1037063, 10011819, 1012047, 10015172, 18376, 1032053,
                1042047, 1042027, 1032027, 93818, 20129, 10019903, 11977, 4058, 10018350, 12023, 11677, 1031009,
                1011019, 11020420, 10114106, 10214106, 1012068, 11238, 10120052, 20499, 10013149, 18192, 10018338, 4615,
                1001009, 1011026, 1005114, 12108, 1001251, 1251, 1001026, 16161, 23625, 93630, 99033, 1005091, 1045062,
                5096, 93718, 43016, 10011109, 10012061, 1012028, 15077, 7916, 16150, 15101, 11300, 14121, 1056, 99169,
                10118029, 16223, 16122, 16124, 11420420, 11492, 43155, 93823, 1022046, 10011457, 14144, 11001, 10011485,
                1016168, 23232, 93695, 93628, 10023618, 11583, 1006615, 18338, 93619, 9316, 10819297, 10219172, 18082,
                18081, 98940, 19335, 10011300, 90480, 99190, 98961, 11641, 4584, 12036, 98962, 11294, 1002028, 93640,
                11945, 11507, 14355, 12109, 1095, 12179, 11147, 1040, 2073, 18360, 12698, 10116124, 93696, 2041,
                1029003, 10111111, 10814037, 6112, 1012044, 11670, 93605, 10310123, 15261, 18070, 19383, 16213, 11954,
                11886, 11887, 11547, 11549, 6159, 11529, 13523, 23636, 23584, 18364, 19056, 14534, 1024053, 10015121,
                19908, 5165, 5696, 7955, 2043, 11564, 10020081, 1145, 10130, 9019, 12117, 10012108, 93622, 93813,
                1012050, 2050, 10019230, 19206, 99076, 18609, 1119, 93701, 98848, 4673, 6615, 4513, 16542, 14132, 4135,
                2053, 14051, 12155, 14412, 11590, 15121, 11591, 9326, 20077, 20078, 1054, 42135, 1001053, 14052,
                1012069, 18069, 18137, 1011009, 19087, 10019087, 10611282, 2032, 93824, 14106, 1002068, 1002001, 5006,
                2013, 9081, 11177, 1012002, 18075, 20080, 93675, 1012046, 14084, 2068, 10111485, 10018368, 6971,
                10018364, 93626, 18375, 11951, 18144, 10511282, 1116, 10211362 };
        int counter = 0;
        while (canContinue) {
            Thread.sleep(1001);
            RestClient defaultClient = RestClient.create();
            String response = "does not exist";
            do {
                try {
                    counter++;
                    int id = idsFromCsv[index++];
                    System.out.println("Aliment id = " + id);
                    response = defaultClient.get().uri(
                            "https://api.spoonacular.com/food/ingredients/{id}/information?apiKey=6db25c150fbc44eebca76e569f90defc&amount=100&unit=grams&locale=en_US",
                            id)
                            .retrieve()
                            .body(String.class);
                } catch (Exception e) {
                    System.err.println("error");
                }
            } while ((response.contains("does not exist") || response.length() < 15) && counter < 152);

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            AlimentDAO alimentDAO = new AlimentDAO();
            AlimentJTO alimentJTO = new AlimentJTO();
            try {
                alimentJTO = mapper.readValue(response, AlimentJTO.class);
                alimentDAO = alimentService.save(MapperAliment.mapAliment(alimentJTO));
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            if (!alimentDAO.equals(new AlimentDAO())) {
                alimentService.save(alimentDAO);
            } else {
                canContinue = false;
                return new ResponseEntity<AlimentDAO>(alimentService.save(alimentDAO), HttpStatus.CONFLICT);
            }
        }
        return null;
    }

    @GetMapping("recipe/addBulkFrom/{index}")
    public String addMultipleRecipes(@PathVariable int index) throws InterruptedException {

        RestClient defaultClient = RestClient.create();
        String response = defaultClient.get().uri(
                "https://api.spoonacular.com/recipes/complexSearch?apiKey=6db25c150fbc44eebca76e569f90defc&addRecipeInformation=true&addRecipeInstructions=true&addRecipeNutrition=true&offset={index}&number=10",
                index)
                .retrieve()
                .body(String.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        RecipeDAO recipeDAO = new RecipeDAO();
        RecipeTransferJTO recipeTransfer = new RecipeTransferJTO();

        System.out.println("offset is : " + index);
        try {
            recipeTransfer = mapper.readValue(response, RecipeTransferJTO.class);

            for (RecipeJTO result : recipeTransfer.getResults()) {

                recipeDAO = mapperRecipe.mapRecipe(result);

                if (!recipeDAO.equals(new RecipeDAO())) {
                    recipeService.save(recipeDAO);
                }

            }

        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping("recipe/addAll")
    public String addAllRecipe() throws InterruptedException {
        for (int i = 0; i < 1000; i += 100) {
            addMultipleRecipes(i);
        }
        return "All Recipes added or updated";
    }

    @GetMapping("recipe/add/{id}")
    public String addOneRecipe(@PathVariable String id) throws InterruptedException {

        RestClient defaultClient = RestClient.create();
        String response = defaultClient.get().uri(
                "https://api.spoonacular.com/recipes/{id}/information?apiKey=6db25c150fbc44eebca76e569f90defc&includeNutrition=true",
                id)
                .retrieve()
                .body(String.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        RecipeDAO recipeDAO = new RecipeDAO();

        try {
            RecipeJTO recipeJTO = mapper.readValue(response, RecipeJTO.class);
            recipeDAO = mapperRecipe.mapRecipe(recipeJTO);

            if (!recipeDAO.equals(new RecipeDAO())) {
                recipeService.save(recipeDAO);
            }

        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping("/missingIngredients/calculate")
    public MissingIngredients calculateMissingIngredients() {

        Iterable<RecipeDAO> allRecipes = recipeService.getAllRecipes();
        Set<String> missingSet = new HashSet<>();

        for (RecipeDAO recipe : allRecipes) {
            List<IngredientDAO> ingredients = recipe.getIngredients();

            for (IngredientDAO ingredient : ingredients) {
                String idIngredient = ingredient.getAliment().getId();
                Optional<AlimentDAO> aliment = alimentService.getAlimentById(idIngredient);

                if (!aliment.isPresent()) {
                    missingSet.add(idIngredient);
                }
            }
        }

        MissingIngredients missingIngredients = new MissingIngredients(1, missingSet, missingSet.size());
        missingService.save(missingIngredients);

        return missingIngredients;
    }

    @GetMapping("/missingIngredients/ponderate")
    public MissingIngredients ponderateMissingIngredients() {

        Iterable<RecipeDAO> allRecipes = recipeService.getAllRecipes();
        Map<String, Integer> missingMap = new HashMap<>();

        for (RecipeDAO recipe : allRecipes) {
            List<IngredientDAO> ingredients = recipe.getIngredients();

            for (IngredientDAO ingredient : ingredients) {
                String idIngredient = ingredient.getAliment().getId();
                Optional<AlimentDAO> aliment = alimentService.getAlimentById(idIngredient);

                if (!aliment.isPresent()) {
                    Integer value = missingMap.get(idIngredient);
                    missingMap.put(idIngredient, value == null ? 1 : value + 1);
                }
            }
        }

        Stream<Map.Entry<String, Integer>> sorted = missingMap.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));

        LinkedHashMap<String, Integer> sortedMissingMap = sorted
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (x, y) -> y, LinkedHashMap::new));

        MissingIngredients missingIngredients = missingService.getMissingById(1).get();
        missingIngredients.setMissingMap(sortedMissingMap);
        missingService.save(missingIngredients);

        return missingIngredients;
    }

    @GetMapping("/missingIngredients/cure/{number}")
    public MissingIngredients cureMissingIngredients(@PathVariable int number) throws InterruptedException {
        int counter = 0;
        Map<String, Integer> missingMap = missingService.getMissingById(1).get().getMissingMap();

        // for (String idAliment :
        // missingMap.stream().limit(number-1).collect(Collectors.toMap())) {
        // addAliment(idAliment);
        // Thread.sleep(1001);
        // }

        for (Map.Entry<String, Integer> entry : missingMap.entrySet().stream().limit(number - 1)
                .collect(Collectors.toSet())) {
            addAliment(entry.getKey());
            if (++counter == 60) {
                System.out.println("Début de la pause");
                Thread.sleep(60000);
                System.out.println("Fin de la pause");
                counter = 0;
            }
        }

        // MissingIngredients missingIngredients = calculateMissingIngredients();
        return null;
    }

    @GetMapping("/missingIngredients/patch")
    public String patchMissingIngredients() {

        Iterable<RecipeDAO> allRecipes = recipeService.getAllRecipes();

        for (RecipeDAO recipe : allRecipes) {
            List<IngredientDAO> ingredients = recipe.getIngredients();

            for (IngredientDAO ingredient : ingredients) {
                String idIngredient = ingredient.getAliment().getId();
                Optional<AlimentDAO> aliment = alimentService.getAlimentById(idIngredient);

                if (aliment.isPresent()) {
                    ingredient.setAliment(aliment.get());
                }
            }
            recipeService.save(recipe);
        }

        return "done";
    }

    @GetMapping("/aliment/all")
    public Set<String> getAllAlliment() {

        Iterable<AlimentDAO> allAliments = alimentService.getAllAliments();
        Set<String> foodSet = new HashSet<>();

        for (AlimentDAO aliment : allAliments) {
            String alimentName = aliment.getNameEn();
            foodSet.add(alimentName);
        }

        return foodSet;
    }

    @GetMapping("/aliment/getConverter")
    public String getAllConvertionValue() throws IOException, URISyntaxException {

        Iterable<RecipeDAO> allRecipes = recipeService.getAllRecipes();
        // Optional<RecipeDAO> recipe = recipeService.getRecipeById("640941");

        Document document = null;
        String uri = null;

        for (RecipeDAO recipe : allRecipes) {
            List<IngredientDAO> ingredientsRecipe = recipe.getIngredients();
            uri = recipe.getSpoonacularURL();
            try {
                document = Jsoup.connect(uri).get();
                Elements ingredientsDOM = document.select(".spoonacular-ingredient");

                for (Element ingredient : ingredientsDOM) {

                    String nameDOM = ingredient.select(".spoonacular-name").text();

                    String qtyFr = ingredient.select(".spoonacular-metric").text();
                    String unitFr = "fail";
                    Double amountFr = Double.valueOf(ingredient.select(".spoonacular-metric").attr("amount"));

                    String qtyUs = ingredient.select(".spoonacular-us").text();
                    String unitUs = "fail2";
                    Double amountUs = Double.valueOf(ingredient.select(".spoonacular-us").attr("amount"));

                    if (qtyFr.split(" ").length < 2 || qtyUs.split(" ").length < 2) {
                        continue;
                    } else {
                        unitFr = qtyFr.split(" ")[1];
                        unitUs = qtyUs.split(" ")[1];
                    }

                    Double convertionValue = amountFr / amountUs;

                    if (unitUs.toLowerCase().contains("cup") || unitUs.trim().toLowerCase().equals("c")) {
                        for (IngredientDAO ingredientDAO : ingredientsRecipe) {

                            AlimentDAO currentAliment = ingredientDAO.getAliment();
                            String nameDAO = ingredientDAO.getNameFromApi();
                            String altNameDAO = currentAliment.getNameEn();

                            if ( (nameDAO == null || nameDAO.equals("")) && (altNameDAO == null || altNameDAO.equals(""))) {
                                break;
                            } else if (nameDAO == null) {
                                nameDAO = "fail231345";
                            } else if (altNameDAO == null) {
                                altNameDAO = "fail1565478";
                            }
                            if ((nameDOM == null || nameDOM.equals(""))) {
                                nameDOM = "fail also 1456";
                                System.err.println("BIG PROBLEM");
                                break;
                            }
                        
                            if (nameDAO.contains(nameDOM)
                            || altNameDAO.contains(nameDOM)
                            || nameDOM.contains(nameDAO)
                            || nameDOM.contains(altNameDAO)) {
                                if (currentAliment.getConvertionValue() == null || currentAliment.getConvertionValue() == 0) {
                                    if (unitFr.toLowerCase().contains("g")) {
                                        currentAliment.setConvertionValue(convertionValue);
                                    } else {
                                        currentAliment.setConvertionValue(23.6588);
                                    }
                                    alimentService.save(currentAliment);
                                }
                                break;
                            }
                        }
                    }
                }
                recipe.setIngredients(ingredientsRecipe);
                recipeService.save(recipe);
            } catch (IndexOutOfBoundsException oob) {
                System.err.println("###############################");
                System.err.println(" ça n'a pas marché avec \n recette : " + recipe.getId());
                for (IngredientDAO ingredientDAO : ingredientsRecipe) {
                    System.err.println(ingredientDAO.getNameFromApi());
                }
                System.err.println("###############################");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ("done");
    }

}
