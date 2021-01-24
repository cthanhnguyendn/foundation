package com.smartcode.foundation.domain.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "author")
public class Author implements Serializable {

    @Id
    private Long id;

    @CreatedBy
    private Long creatorId;
    @LastModifiedBy
    private Long modifierId;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    private String fullName;
    private String about;
    private String nationality;

//    private Set<String> genres = new HashSet<>();
}