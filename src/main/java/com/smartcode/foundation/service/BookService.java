package com.smartcode.foundation.service;

import com.smartcode.foundation.domain.dto.BookView;
import com.smartcode.foundation.domain.dto.EditBookRequest;
import com.smartcode.foundation.domain.dto.Page;
import com.smartcode.foundation.domain.dto.SearchBooksQuery;
import com.smartcode.foundation.domain.mapper.BookEditMapper;
import com.smartcode.foundation.domain.mapper.BookViewMapper;
import com.smartcode.foundation.domain.model.Author;
import com.smartcode.foundation.domain.model.Book;
import com.smartcode.foundation.repository.AuthorRepo;
import com.smartcode.foundation.repository.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;
    private final BookEditMapper bookEditMapper;
    private final BookViewMapper bookViewMapper;

    @Transactional
    public BookView create(EditBookRequest request) {
        Book book = bookEditMapper.create(request);

        book = bookRepo.save(book);
        updateAuthors(book);

        return bookViewMapper.toBookView(book);
    }

    @Transactional
    public BookView update(Long id, EditBookRequest request) {
        Book book = bookRepo.getById(id);
        bookEditMapper.update(request, book);

        book = bookRepo.save(book);
//        if (!CollectionUtils.isEmpty(request.getAuthorIds())) {
//            updateAuthors(book);
//        }

        return bookViewMapper.toBookView(book);
    }

    private void updateAuthors(Book book) {
//        List<Author> authors = authorRepo.findAllById(book.getAuthorIds());
//        authors.forEach(author -> author.getBookIds().add(book.getId()));
//        authorRepo.saveAll(authors);
    }

    @Transactional
    public BookView delete(Long id) {
        Book book = bookRepo.getById(id);

        bookRepo.delete(book);

        return bookViewMapper.toBookView(book);
    }

    public BookView getBook(Long id) {
        Book book = bookRepo.getById(id);
        return bookViewMapper.toBookView(book);
    }

    public List<BookView> getBooks(Iterable<Long> ids) {
        List<Book> books = bookRepo.findAllById(ids);
        return bookViewMapper.toBookView(books);
    }

    public List<BookView> getAuthorBooks(Long authorId) {
        Author author = authorRepo.getById(authorId);
        return new ArrayList<>();
    }

    public List<BookView> searchBooks(Page page, SearchBooksQuery query) {
        return bookViewMapper.toBookView(bookRepo.searchBooks(page, query));
    }

}
