package com.smartcode.foundation.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
class AuthRole implements GrantedAuthority{
    String role;
    @Override
    public String getAuthority() {
        return role;
    }
}
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailView implements UserDetails {
    private String id;
    private String username;
    private String password;
    private Set<String> roles;

    public UserDetailView(String userId, String username, Set<String> roles) {
        this.username = username;
        this.id = userId;
        this.roles = roles;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(r -> new AuthRole(r)).collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
