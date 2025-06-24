package com.jlgdev.ceres.models.mapper;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.jlgdev.ceres.models.dataTransferObject.AlimentDTO;
import com.jlgdev.ceres.models.dataTransferObject.AlimentPropertiesDTO;
import com.jlgdev.ceres.models.dataTransferObject.FlavonoidDTO;
import com.jlgdev.ceres.models.dataTransferObject.NutrientDTO;
import com.jlgdev.ceres.models.dataTransferObject.NutritionDTO;
import com.jlgdev.ceres.models.dataTransferObject.WeightPerServingDTO;
import com.jlgdev.ceres.models.jsonToObject.AlimentJTO;
import com.jlgdev.ceres.models.jsonToObject.AlimentPropertiesJTO;
import com.jlgdev.ceres.models.jsonToObject.FlavonoidJTO;
import com.jlgdev.ceres.models.jsonToObject.NutrientJTO;
import com.jlgdev.ceres.models.jsonToObject.NutritionJTO;
import com.jlgdev.ceres.models.jsonToObject.WeightPerServingJTO;

@Component
public abstract class MapperAliment {

    public static AlimentDTO mapAliment(AlimentJTO alimentJTO) {

        AlimentDTO alimentDAO = new AlimentDTO(); 

        alimentDAO.setId(Objects.requireNonNullElse(alimentJTO.getId(),""));
        alimentDAO.setNameEn(Objects.requireNonNullElse(alimentJTO.getName(),""));
        alimentDAO.setAisle(Objects.requireNonNullElse(alimentJTO.getAisle(),""));
        alimentDAO.setImage(Objects.requireNonNullElse(alimentJTO.getImage(),""));
        alimentDAO.setNutrition(mapNutritionAliment(alimentJTO.getNutrition()));
        alimentDAO.setCategoryPath(alimentJTO.getCategoryPath());
        alimentDAO.setPossibleUnits(alimentJTO.getPossibleUnit());

        return alimentDAO;
    }

    public static NutritionDTO mapNutritionAliment(NutritionJTO nutritionJTO) {
    
        NutritionDTO nutritionDAO = new NutritionDTO();

        nutritionDAO.setNutrients(mapNutrients(nutritionJTO.getNutrients()));
        nutritionDAO.setProperties(mapProperties(nutritionJTO.getProperties()));
        nutritionDAO.setFlavonoids(mapFlavonoids(nutritionJTO.getFlavonoids()));
        nutritionDAO.setCaloricBreakdown(nutritionJTO.getCaloricBreakdown());
        nutritionDAO.setWeightPerServing(mapWeightPerServing(nutritionJTO.getWeightPerServing()));

        return nutritionDAO;
    }

    public static Set<NutrientDTO> mapNutrients(Set<NutrientJTO> nutrientsJTO) {

        Set<NutrientDTO> nutrients = new HashSet<>();

        for (NutrientJTO nutrientJTO : nutrientsJTO) {
            NutrientDTO nutrientDAO = new NutrientDTO();
            
            nutrientDAO.setNameEn(Objects.requireNonNullElse(nutrientJTO.getName(), ""));
            nutrientDAO.setAmount(Objects.requireNonNullElse(nutrientJTO.getAmount(), -1.0));
            nutrientDAO.setUnit(Objects.requireNonNullElse(nutrientJTO.getUnit(), ""));
            nutrientDAO.setPercentOfDailyNeed(Objects.requireNonNullElse(nutrientJTO.getPercentOfDailyNeed(), -1.0));

            nutrients.add(nutrientDAO);
        }

        return nutrients;
    }
    
    public static Set<AlimentPropertiesDTO> mapProperties(Set<AlimentPropertiesJTO> propertiesJTO) {
        
        Set<AlimentPropertiesDTO> properties = new HashSet<>();

        for (AlimentPropertiesJTO propertyJTO : propertiesJTO) {
            AlimentPropertiesDTO propertyDAO = new AlimentPropertiesDTO();
            
            propertyDAO.setNameEn(Objects.requireNonNullElse(propertyJTO.getName(), ""));
            propertyDAO.setAmount(Objects.requireNonNullElse(propertyJTO.getAmount(), -1.0));
            propertyDAO.setUnit(Objects.requireNonNullElse(propertyJTO.getUnit(), ""));
            
            properties.add(propertyDAO);
        }
        
        return properties;
    }
    
    public static Set<FlavonoidDTO> mapFlavonoids(Set<FlavonoidJTO> flavonoidsJTO) {
        
        Set<FlavonoidDTO> flavonoids = new HashSet<>();
        
        for (FlavonoidJTO flavonoidJTO : flavonoidsJTO) {
            FlavonoidDTO flavonoidDAO = new FlavonoidDTO();
            
            flavonoidDAO.setNameEn(Objects.requireNonNullElse(flavonoidJTO.getName(), ""));
            flavonoidDAO.setAmount(Objects.requireNonNullElse(flavonoidJTO.getAmount(), -1.0));
            flavonoidDAO.setUnit(Objects.requireNonNullElse(flavonoidJTO.getUnit(), ""));
            
            flavonoids.add(flavonoidDAO);
        }

        return flavonoids;
    }

    public static WeightPerServingDTO mapWeightPerServing(WeightPerServingJTO weightPerServingJTO) {

        WeightPerServingDTO weightPerServingDAO = new WeightPerServingDTO(Objects.requireNonNullElse(weightPerServingJTO.getAmount(),-1.0), Objects.requireNonNullElse(weightPerServingJTO.getUnit(), ""));

        return weightPerServingDAO;
    }

}
