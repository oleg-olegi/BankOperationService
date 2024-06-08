package com.example.bankoperationservice.mapper;

import com.example.bankoperationservice.dto.ContactDTO;
import com.example.bankoperationservice.dto.RegisterDTO;
import com.example.bankoperationservice.model.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Lazy;

@Lazy
@Mapper(componentModel = "spring")
public interface ContactMapper {
    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

//    @Mapping(source = "phone", target = "phones")
    Contact dtoToContact(RegisterDTO registerDTO);

    Contact dtoToContact(ContactDTO contactDTO);

    ContactDTO contactToDto(Contact contact);
}
