package com.idris.membership.controller;

import com.idris.membership.common.CommonResponse;
import com.idris.membership.payloads.request.LoginRequest;
import com.idris.membership.payloads.request.RegistrationRequest;
import com.idris.membership.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody @Valid RegistrationRequest request) {
        try {
            return ResponseEntity.ok(authService.registration(request));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.ok(new CommonResponse(500, ex.getMessage(), null));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
        try {
            return ResponseEntity.ok(authService.login(request));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.ok(new CommonResponse(500, ex.getMessage(), null));
        }
    }
}
