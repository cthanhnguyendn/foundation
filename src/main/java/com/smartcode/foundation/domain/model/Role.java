package com.smartcode.foundation.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {

    public static final String USER_ADMIN = "USER_ADMIN";
    public static final String AUTHOR_ADMIN = "AUTHOR_ADMIN";
    public static final String BOOK_ADMIN = "BOOK_ADMIN";



    @Id
    private String authority;
    @ManyToMany
    Set<User> users;

    public Role(String authority) {
        this.authority = authority;
    }
}
