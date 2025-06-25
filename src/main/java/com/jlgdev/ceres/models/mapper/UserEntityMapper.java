package com.jlgdev.ceres.models.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.jlgdev.ceres.models.dataTransferObject.SQL.UserEntityCreateDTO;
import com.jlgdev.ceres.models.dataTransferObject.SQL.UserEntityReadDTO;
import com.jlgdev.ceres.models.dataTransferObject.SQL.UserEntityUpdateDTO;
import com.jlgdev.ceres.models.dataTransferObject.SQL.UserPreferenceCreateDTO;
import com.jlgdev.ceres.models.dataTransferObject.SQL.UserPreferenceReadDTO;
import com.jlgdev.ceres.models.dataTransferObject.SQL.UserPreferenceUpdateDTO;
import com.jlgdev.ceres.models.entities.UserEntity;
import com.jlgdev.ceres.models.entities.UserPreference;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    UserEntity toCreateEntity(UserEntityCreateDTO userEntityCreateDTO);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserPreference toCreateEntity(UserPreferenceCreateDTO userPreferenceCreateDTO);

    UserEntityReadDTO toReadDTO(UserEntity userEntity);
    UserPreferenceReadDTO toReadDTO(UserPreference userPreferenceEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(UserEntityUpdateDTO userEntityUpdateDTO, @MappingTarget UserEntity userEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(UserPreferenceUpdateDTO userPreferenceUpdateDTO, @MappingTarget UserPreference userPreference);

}
