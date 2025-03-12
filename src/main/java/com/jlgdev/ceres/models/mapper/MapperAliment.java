package com.jlgdev.ceres.models.mapper;

import java.util.HashSet;
import java.util.Set;

import com.jlgdev.ceres.models.dataAccessObject.AlimentDAO;
import com.jlgdev.ceres.models.dataAccessObject.AlimentPropertiesDAO;
import com.jlgdev.ceres.models.dataAccessObject.FlavonoidDAO;
import com.jlgdev.ceres.models.dataAccessObject.NutrientDAO;
import com.jlgdev.ceres.models.dataAccessObject.NutritionDAO;
import com.jlgdev.ceres.models.dataAccessObject.WeightPerServingDAO;
import com.jlgdev.ceres.models.jsonToObject.AlimentJTO;
import com.jlgdev.ceres.models.jsonToObject.AlimentPropertiesJTO;
import com.jlgdev.ceres.models.jsonToObject.FlavonoidJTO;
import com.jlgdev.ceres.models.jsonToObject.NutrientJTO;
import com.jlgdev.ceres.models.jsonToObject.NutritionJTO;
import com.jlgdev.ceres.models.jsonToObject.WeightPerServingJTO;

public abstract class MapperAliment {

    public static AlimentDAO mapAliment(AlimentJTO alimentJTO) {

        AlimentDAO alimentDAO = new AlimentDAO(); 

        alimentDAO.setId(alimentJTO.getId());
        alimentDAO.setNameEn(alimentJTO.getName());
        alimentDAO.setAisle(alimentJTO.getAisle());
        alimentDAO.setImage(alimentJTO.getImage());
        alimentDAO.setNutrition(mapNutritionAliment(alimentJTO.getNutrition()));
        alimentDAO.setCategoryPath(alimentJTO.getCategoryPath());

        return alimentDAO;
    }

    public static NutritionDAO mapNutritionAliment(NutritionJTO nutritionJTO) {
    
        NutritionDAO nutritionDAO = new NutritionDAO();

        nutritionDAO.setNutrients(mapNutrients(nutritionJTO.getNutrients()));
        nutritionDAO.setProperties(mapProperties(nutritionJTO.getProperties()));
        nutritionDAO.setFlavonoids(mapFlavonoids(nutritionJTO.getFlavonoids()));
        nutritionDAO.setCaloricBreakdown(nutritionJTO.getCaloricBreakdown());
        nutritionDAO.setWeightPerServing(mapWeightPerServing(nutritionJTO.getWeightPerServing()));

        return nutritionDAO;
    }

    public static Set<NutrientDAO> mapNutrients(Set<NutrientJTO> nutrientsJTO) {

        Set<NutrientDAO> nutrients = new HashSet<>();

        for (NutrientJTO nutrientJTO : nutrientsJTO) {
            NutrientDAO nutrientDAO = new NutrientDAO();
            
            nutrientDAO.setNameEn(nutrientJTO.getName());
            nutrientDAO.setAmount(nutrientJTO.getAmount());
            nutrientDAO.setUnit(nutrientJTO.getUnit());
            nutrientDAO.setPercentOfDailyNeed(nutrientJTO.getPercentOfDailyNeed());

            nutrients.add(nutrientDAO);
        }

        return nutrients;
    }
    
    public static Set<AlimentPropertiesDAO> mapProperties(Set<AlimentPropertiesJTO> propertiesJTO) {
        
        Set<AlimentPropertiesDAO> properties = new HashSet<>();

        for (AlimentPropertiesJTO propertyJTO : propertiesJTO) {
            AlimentPropertiesDAO propertyDAO = new AlimentPropertiesDAO();
            
            propertyDAO.setNameEn(propertyJTO.getName());
            propertyDAO.setAmount(propertyJTO.getAmount());
            
            properties.add(propertyDAO);
        }

        return properties;
    }
    
    public static Set<FlavonoidDAO> mapFlavonoids(Set<FlavonoidJTO> flavonoidsJTO) {

        Set<FlavonoidDAO> flavonoids = new HashSet<>();

        for (FlavonoidJTO flavonoidJTO : flavonoidsJTO) {
            FlavonoidDAO flavonoidDAO = new FlavonoidDAO();
            
            flavonoidDAO.setNameEn(flavonoidJTO.getName());
            flavonoidDAO.setAmount(flavonoidJTO.getAmount());
            
            flavonoids.add(flavonoidDAO);
        }

        return flavonoids;
    }

    public static WeightPerServingDAO mapWeightPerServing(WeightPerServingJTO weightPerServingJTO) {

        WeightPerServingDAO weightPerServingDAO = new WeightPerServingDAO(weightPerServingJTO.getAmount(), weightPerServingJTO.getUnit());

        return weightPerServingDAO;
    }
}
