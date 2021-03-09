package com.smartcode.foundation.service;

import com.smartcode.foundation.domain.dto.UserDetailView;
import com.smartcode.foundation.domain.model.User;
import com.smartcode.foundation.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@AllArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final UserRepo userRepo;
    @Transactional
    public UserDetailView loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                format("User: %s, not found", username)
        ));
        UserDetailView uv = new UserDetailView();
        uv.setUsername(user.getUsername());
        uv.setRoles(user.getRoles().stream().map(au -> au.getRole()).collect(Collectors.toSet()));
        uv.setPassword(user.getPassword());
        return uv;
    }}
