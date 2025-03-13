package com.jlgdev.ceres.models.mapper;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.stereotype.Component;

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

@Component
public abstract class MapperAliment {

    public static AlimentDAO mapAliment(AlimentJTO alimentJTO) {

        AlimentDAO alimentDAO = new AlimentDAO(); 

        alimentDAO.setId(Objects.requireNonNullElse(alimentJTO.getId(),""));
        alimentDAO.setNameEn(Objects.requireNonNullElse(alimentJTO.getName(),""));
        alimentDAO.setAisle(Objects.requireNonNullElse(alimentJTO.getAisle(),""));
        alimentDAO.setImage(Objects.requireNonNullElse(alimentJTO.getImage(),""));
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
            
            nutrientDAO.setNameEn(Objects.requireNonNullElse(nutrientJTO.getName(), ""));
            nutrientDAO.setAmount(Objects.requireNonNullElse(nutrientJTO.getAmount(), -1.0));
            nutrientDAO.setUnit(Objects.requireNonNullElse(nutrientJTO.getUnit(), ""));
            nutrientDAO.setPercentOfDailyNeed(Objects.requireNonNullElse(nutrientJTO.getPercentOfDailyNeed(), -1.0));

            nutrients.add(nutrientDAO);
        }

        return nutrients;
    }
    
    public static Set<AlimentPropertiesDAO> mapProperties(Set<AlimentPropertiesJTO> propertiesJTO) {
        
        Set<AlimentPropertiesDAO> properties = new HashSet<>();

        for (AlimentPropertiesJTO propertyJTO : propertiesJTO) {
            AlimentPropertiesDAO propertyDAO = new AlimentPropertiesDAO();
            
            propertyDAO.setNameEn(Objects.requireNonNullElse(propertyJTO.getName(), ""));
            propertyDAO.setAmount(Objects.requireNonNullElse(propertyJTO.getAmount(), -1.0));
            propertyDAO.setUnit(Objects.requireNonNullElse(propertyJTO.getUnit(), ""));
            
            properties.add(propertyDAO);
        }
        
        return properties;
    }
    
    public static Set<FlavonoidDAO> mapFlavonoids(Set<FlavonoidJTO> flavonoidsJTO) {
        
        Set<FlavonoidDAO> flavonoids = new HashSet<>();
        
        for (FlavonoidJTO flavonoidJTO : flavonoidsJTO) {
            FlavonoidDAO flavonoidDAO = new FlavonoidDAO();
            
            flavonoidDAO.setNameEn(Objects.requireNonNullElse(flavonoidJTO.getName(), ""));
            flavonoidDAO.setAmount(Objects.requireNonNullElse(flavonoidJTO.getAmount(), -1.0));
            flavonoidDAO.setUnit(Objects.requireNonNullElse(flavonoidJTO.getUnit(), ""));
            
            flavonoids.add(flavonoidDAO);
        }

        return flavonoids;
    }

    public static WeightPerServingDAO mapWeightPerServing(WeightPerServingJTO weightPerServingJTO) {

        WeightPerServingDAO weightPerServingDAO = new WeightPerServingDAO(Objects.requireNonNullElse(weightPerServingJTO.getAmount(),-1.0), Objects.requireNonNullElse(weightPerServingJTO.getUnit(), ""));

        return weightPerServingDAO;
    }

}
