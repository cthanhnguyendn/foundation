package com.smartcode.foundation.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginCertificateView {
    private String token;
    private String username;
}
