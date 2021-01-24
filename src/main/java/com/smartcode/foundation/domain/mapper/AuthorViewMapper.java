package com.smartcode.foundation.domain.mapper;

import com.smartcode.foundation.domain.dto.AuthorView;
import com.smartcode.foundation.domain.model.Author;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public abstract class AuthorViewMapper {

    private UserViewMapper userViewMapper;

    @Autowired
    public void setUserViewMapper(UserViewMapper userViewMapper) {
        this.userViewMapper = userViewMapper;
    }
    @BeanMapping(nullValueCheckStrategy = ALWAYS, nullValuePropertyMappingStrategy = IGNORE)
    public abstract AuthorView toAuthorView(Author author);
    @BeanMapping(nullValueCheckStrategy = ALWAYS, nullValuePropertyMappingStrategy = IGNORE)
    public abstract List<AuthorView> toAuthorView(List<Author> authors);

    @AfterMapping
    @BeanMapping(nullValueCheckStrategy = ALWAYS, nullValuePropertyMappingStrategy = IGNORE)
    protected void after(Author author, @MappingTarget AuthorView authorView) {
        authorView.setCreator(userViewMapper.toUserViewById(author.getCreatorId()));
    }

}
