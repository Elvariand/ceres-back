package com.jlgdev.ceres.models.mapper;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.jlgdev.ceres.models.dataTransferObject.NoSQL.AlimentDTO;
import com.jlgdev.ceres.models.dataTransferObject.NoSQL.AlimentPropertiesDTO;
import com.jlgdev.ceres.models.dataTransferObject.NoSQL.FlavonoidDTO;
import com.jlgdev.ceres.models.dataTransferObject.NoSQL.NutrientDTO;
import com.jlgdev.ceres.models.dataTransferObject.NoSQL.NutritionDTO;
import com.jlgdev.ceres.models.dataTransferObject.NoSQL.WeightPerServingDTO;
import com.jlgdev.ceres.models.jsonToObject.AlimentJTO;
import com.jlgdev.ceres.models.jsonToObject.AlimentPropertiesJTO;
import com.jlgdev.ceres.models.jsonToObject.FlavonoidJTO;
import com.jlgdev.ceres.models.jsonToObject.NutrientJTO;
import com.jlgdev.ceres.models.jsonToObject.NutritionJTO;
import com.jlgdev.ceres.models.jsonToObject.WeightPerServingJTO;

@Component
public abstract class AlimentMapper {

    public static AlimentDTO mapAliment(AlimentJTO alimentJTO) {

        AlimentDTO alimentDTO = new AlimentDTO(); 

        alimentDTO.setId(Objects.requireNonNullElse(alimentJTO.getId(),""));
        alimentDTO.setNameEn(Objects.requireNonNullElse(alimentJTO.getName(),""));
        alimentDTO.setAisle(Objects.requireNonNullElse(alimentJTO.getAisle(),""));
        alimentDTO.setImage(Objects.requireNonNullElse(alimentJTO.getImage(),""));
        alimentDTO.setNutrition(mapNutritionAliment(alimentJTO.getNutrition()));
        alimentDTO.setCategoryPath(alimentJTO.getCategoryPath());
        alimentDTO.setPossibleUnits(alimentJTO.getPossibleUnit());

        return alimentDTO;
    }

    public static NutritionDTO mapNutritionAliment(NutritionJTO nutritionJTO) {
    
        NutritionDTO nutritionDTO = new NutritionDTO();

        nutritionDTO.setNutrients(mapNutrients(nutritionJTO.getNutrients()));
        nutritionDTO.setProperties(mapProperties(nutritionJTO.getProperties()));
        nutritionDTO.setFlavonoids(mapFlavonoids(nutritionJTO.getFlavonoids()));
        nutritionDTO.setCaloricBreakdown(nutritionJTO.getCaloricBreakdown());
        nutritionDTO.setWeightPerServing(mapWeightPerServing(nutritionJTO.getWeightPerServing()));

        return nutritionDTO;
    }

    public static Set<NutrientDTO> mapNutrients(Set<NutrientJTO> nutrientsJTO) {

        Set<NutrientDTO> nutrients = new HashSet<>();

        for (NutrientJTO nutrientJTO : nutrientsJTO) {
            NutrientDTO nutrientDTO = new NutrientDTO();
            
            nutrientDTO.setNameEn(Objects.requireNonNullElse(nutrientJTO.getName(), ""));
            nutrientDTO.setAmount(Objects.requireNonNullElse(nutrientJTO.getAmount(), -1.0));
            nutrientDTO.setUnit(Objects.requireNonNullElse(nutrientJTO.getUnit(), ""));
            nutrientDTO.setPercentOfDailyNeed(Objects.requireNonNullElse(nutrientJTO.getPercentOfDailyNeed(), -1.0));

            nutrients.add(nutrientDTO);
        }

        return nutrients;
    }
    
    public static Set<AlimentPropertiesDTO> mapProperties(Set<AlimentPropertiesJTO> propertiesJTO) {
        
        Set<AlimentPropertiesDTO> properties = new HashSet<>();

        for (AlimentPropertiesJTO propertyJTO : propertiesJTO) {
            AlimentPropertiesDTO propertyDTO = new AlimentPropertiesDTO();
            
            propertyDTO.setNameEn(Objects.requireNonNullElse(propertyJTO.getName(), ""));
            propertyDTO.setAmount(Objects.requireNonNullElse(propertyJTO.getAmount(), -1.0));
            propertyDTO.setUnit(Objects.requireNonNullElse(propertyJTO.getUnit(), ""));
            
            properties.add(propertyDTO);
        }
        
        return properties;
    }
    
    public static Set<FlavonoidDTO> mapFlavonoids(Set<FlavonoidJTO> flavonoidsJTO) {
        
        Set<FlavonoidDTO> flavonoids = new HashSet<>();
        
        for (FlavonoidJTO flavonoidJTO : flavonoidsJTO) {
            FlavonoidDTO flavonoidDTO = new FlavonoidDTO();
            
            flavonoidDTO.setNameEn(Objects.requireNonNullElse(flavonoidJTO.getName(), ""));
            flavonoidDTO.setAmount(Objects.requireNonNullElse(flavonoidJTO.getAmount(), -1.0));
            flavonoidDTO.setUnit(Objects.requireNonNullElse(flavonoidJTO.getUnit(), ""));
            
            flavonoids.add(flavonoidDTO);
        }

        return flavonoids;
    }

    public static WeightPerServingDTO mapWeightPerServing(WeightPerServingJTO weightPerServingJTO) {

        WeightPerServingDTO weightPerServingDTO = new WeightPerServingDTO(Objects.requireNonNullElse(weightPerServingJTO.getAmount(),-1.0), Objects.requireNonNullElse(weightPerServingJTO.getUnit(), ""));

        return weightPerServingDTO;
    }

}
