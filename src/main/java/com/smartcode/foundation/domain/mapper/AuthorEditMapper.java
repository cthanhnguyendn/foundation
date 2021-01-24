package com.smartcode.foundation.domain.mapper;


import com.smartcode.foundation.domain.dto.EditAuthorRequest;
import com.smartcode.foundation.domain.model.Author;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public interface AuthorEditMapper {
    @BeanMapping(nullValueCheckStrategy = ALWAYS, nullValuePropertyMappingStrategy = IGNORE)
    Author create(EditAuthorRequest request);

    @BeanMapping(nullValueCheckStrategy = ALWAYS, nullValuePropertyMappingStrategy = IGNORE)
    void update(EditAuthorRequest request, @MappingTarget Author author);

}
