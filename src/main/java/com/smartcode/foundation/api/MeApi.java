package com.smartcode.foundation.api;

import com.smartcode.foundation.domain.dto.UserDetailView;
import com.smartcode.foundation.domain.dto.UserView;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "me")
@RestController
@RequestMapping(path = "api")
@RequiredArgsConstructor
public class MeApi {
    @GetMapping("me")
    public ResponseEntity<UserView> getMe(@AuthenticationPrincipal UserDetailView userDetailView){
        return ResponseEntity.ok(new UserView(userDetailView.getId(), userDetailView.getUsername()));
    }
}
