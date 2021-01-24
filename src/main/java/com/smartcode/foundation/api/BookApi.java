package com.smartcode.foundation.api;

import com.smartcode.foundation.domain.dto.*;
import com.smartcode.foundation.domain.model.Role;
import com.smartcode.foundation.service.AuthorService;
import com.smartcode.foundation.service.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@Tag(name = "Book")
@RestController @RequestMapping(path = "api/book")
@RequiredArgsConstructor
public class BookApi {

    private final BookService bookService;
    private final AuthorService authorService;

    @RolesAllowed(Role.BOOK_ADMIN)
    @PostMapping
    public BookView create(@RequestBody @Valid EditBookRequest request) {
        return bookService.create(request);
    }

    @RolesAllowed(Role.BOOK_ADMIN)
    @PutMapping("{id}")
    public BookView edit(@PathVariable String id, @RequestBody @Valid EditBookRequest request) {
        return bookService.update(Long.parseLong(id), request);
    }

    @RolesAllowed(Role.BOOK_ADMIN)
    @DeleteMapping("{id}")
    public BookView delete(@PathVariable String id) {
        return bookService.delete(Long.parseLong(id));
    }

    @GetMapping("{id}")
    public BookView get(@PathVariable String id) {
        return bookService.getBook(Long.parseLong(id));
    }

    @GetMapping("{id}/author")
    public ListResponse<AuthorView> getAuthors(@PathVariable String id) {
        return new ListResponse<AuthorView>();
    }

    @PostMapping("search")
    public ListResponse<BookView> search(@RequestBody @Valid SearchRequest<SearchBooksQuery> request) {
        return new ListResponse<>(bookService.searchBooks(request.getPage(), request.getQuery()));
    }

}
