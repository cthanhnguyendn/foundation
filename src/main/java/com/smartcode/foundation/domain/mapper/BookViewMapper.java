package com.smartcode.foundation.domain.mapper;

import com.smartcode.foundation.domain.dto.BookView;
import com.smartcode.foundation.domain.model.Book;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class BookViewMapper {

    private UserViewMapper userViewMapper;

    @Autowired
    public void setUserViewMapper(UserViewMapper userViewMapper) {
        this.userViewMapper = userViewMapper;
    }

    public abstract BookView toBookView(Book book);

    public abstract List<BookView> toBookView(List<Book> books);

    @AfterMapping
    protected void after(Book book, @MappingTarget BookView bookView) {
        bookView.setCreator(userViewMapper.toUserViewById(book.getCreatorId()));
    }

}
