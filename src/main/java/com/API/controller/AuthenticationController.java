package com.API.controller;

import java.text.ParseException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.API.Service.AutheticationService;
import com.API.dto.request.ApiResponse;
import com.API.dto.request.AuthenticationRequest;
import com.API.dto.request.IntrospectRequest;
import com.API.dto.request.LogoutRequest;
import com.API.dto.request.RefreshRequest;
import com.API.dto.response.AutheticationReponse;
import com.API.dto.response.IntrospectReponse;
import com.nimbusds.jose.JOSEException;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AutheticationService autheticationService;

    @PostMapping("/login")
    public ApiResponse<AutheticationReponse> authenticate(@RequestBody AuthenticationRequest request) {
        var result = autheticationService.authenticate(request);
        return ApiResponse.<AutheticationReponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectReponse> introspect(@RequestBody IntrospectRequest request)
            throws JOSEException, ParseException {
        var result = autheticationService.introspect(request);
        return ApiResponse.<IntrospectReponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/logout")
    public ApiResponse<Void> logout(@RequestBody LogoutRequest request)
            throws JOSEException, ParseException {
        autheticationService.logout(request);
        return ApiResponse.<Void>builder().build();
    }

    @PostMapping("/refresh")
    public ApiResponse<AutheticationReponse> authenticate(@RequestBody RefreshRequest request)
            throws ParseException, JOSEException {
        var result = autheticationService.refreshToken(request);
        return ApiResponse.<AutheticationReponse>builder()
                .result(result)
                .build();
    }

}
