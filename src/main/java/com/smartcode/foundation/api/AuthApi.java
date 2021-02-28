package com.smartcode.foundation.api;

import com.smartcode.foundation.configuration.security.JwtTokenUtil;
import com.smartcode.foundation.domain.dto.AuthRequest;
import com.smartcode.foundation.domain.dto.CreateUserRequest;
import com.smartcode.foundation.domain.dto.LoginCertificateView;
import com.smartcode.foundation.domain.dto.UserView;
import com.smartcode.foundation.domain.mapper.UserViewMapper;
import com.smartcode.foundation.domain.model.User;
import com.smartcode.foundation.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "Authentication")
@RestController @RequestMapping(path = "api/public")
@RequiredArgsConstructor
public class AuthApi {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserViewMapper userViewMapper;
    private final UserService userService;

    @PostMapping("login")
    public ResponseEntity<LoginCertificateView> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            User user = (User) authenticate.getPrincipal();
            String token = jwtTokenUtil.generateAccessToken(user);
            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, token)
                    .body(new LoginCertificateView(token, user.getUsername()));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("register")
    public LoginCertificateView register(@RequestBody @Valid CreateUserRequest request) {
        UserView newUser = userService.create(request);
        String token = jwtTokenUtil.generateAccessToken(newUser.getId(),newUser.getUsername());
        return new LoginCertificateView(token, newUser.getUsername());
    }

}
