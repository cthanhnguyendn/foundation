package com.smartcode.foundation.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AuthorView {

    private String id;

    private UserView creator;
    private LocalDateTime createdAt;

    private String fullName;
    private String about;
    private String nationality;

}
