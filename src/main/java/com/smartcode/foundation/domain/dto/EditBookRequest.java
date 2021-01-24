package com.smartcode.foundation.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class EditBookRequest {

    @NotNull
    private String title;
    private String about;
    private String language;
    private String isbn13;
    private String isbn10;
    private String publisher;
    private LocalDate publishDate;
    private Integer hardcover;

}
