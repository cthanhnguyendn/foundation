package com.smartcode.foundation.service;

import com.smartcode.foundation.domain.dto.AuthorView;
import com.smartcode.foundation.domain.dto.EditAuthorRequest;
import com.smartcode.foundation.domain.dto.Page;
import com.smartcode.foundation.domain.dto.SearchAuthorsQuery;
import com.smartcode.foundation.domain.mapper.AuthorEditMapper;
import com.smartcode.foundation.domain.mapper.AuthorViewMapper;
import com.smartcode.foundation.domain.model.Author;
import com.smartcode.foundation.domain.model.Book;
import com.smartcode.foundation.repository.AuthorRepo;
import com.smartcode.foundation.repository.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepo authorRepo;
    private final BookRepo bookRepo;
    private final AuthorEditMapper authorEditMapper;
    private final AuthorViewMapper authorViewMapper;

    @Transactional
    public AuthorView create(EditAuthorRequest request) {
        Author author = authorEditMapper.create(request);

        author = authorRepo.save(author);

        return authorViewMapper.toAuthorView(author);
    }

    @Transactional
    public AuthorView update(Long id, EditAuthorRequest request) {
        Author author = authorRepo.getById(id);
        authorEditMapper.update(request, author);

        author = authorRepo.save(author);

        return authorViewMapper.toAuthorView(author);
    }

    @Transactional
    public AuthorView delete(Long id) {
        Author author = authorRepo.getById(id);

        authorRepo.delete(author);
//        bookRepo.deleteAll(bookRepo.findAllById(author.getBookIds()));

        return authorViewMapper.toAuthorView(author);
    }

    public AuthorView getAuthor(Long id) {
        return authorViewMapper.toAuthorView(authorRepo.getById(id));
    }

    public List<AuthorView> getAuthors(Iterable<Long> ids) {
        return authorViewMapper.toAuthorView(authorRepo.findAllById(ids));
    }

    public List<AuthorView> getBookAuthors(Long bookId) {
//        Book book = bookRepo.getById(bookId);
//        return authorViewMapper.toAuthorView(authorRepo.findAllById(book.getAuthorIds()));
        return new ArrayList<AuthorView>();
    }

    public List<AuthorView> searchAuthors(Page page, SearchAuthorsQuery query) {
        return authorViewMapper.toAuthorView(authorRepo.searchAuthors(page, query));
    }

}
