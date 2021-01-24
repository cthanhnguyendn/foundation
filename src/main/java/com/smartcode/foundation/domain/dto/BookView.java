package com.smartcode.foundation.domain.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class BookView {

    private Long Id;

    private UserView creator;
    private LocalDateTime createdAt;

    private String title;
    private String about;
    private String language;
    private String isbn13;
    private String isbn10;
    private String publisher;
    private LocalDate publishDate;
    private int hardcover;

}
