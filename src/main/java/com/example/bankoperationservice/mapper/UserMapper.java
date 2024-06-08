package com.example.bankoperationservice.mapper;

import com.example.bankoperationservice.dto.UserDTO;
import com.example.bankoperationservice.model.UserData;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Lazy;

@Lazy
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserData userDTOtoModel(UserDTO userDTO);
}
