package com.example.bankoperationservice.mapper;

import com.example.bankoperationservice.dto.RegisterDTO;
import com.example.bankoperationservice.model.UserData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RegisterDtoMapper {
    RegisterDtoMapper INSTANCE = Mappers.getMapper(RegisterDtoMapper.class);

    RegisterDTO modelToDto(UserData source);

    @Mapping(target = "login", source = "userName")
    UserData dtoToModel(RegisterDTO registerDTO);
}
