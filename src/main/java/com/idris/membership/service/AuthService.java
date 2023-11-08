package com.idris.membership.service;

import com.idris.membership.common.CommonResponse;
import com.idris.membership.model.User;
import com.idris.membership.payloads.request.LoginRequest;
import com.idris.membership.payloads.request.RegistrationRequest;
import com.idris.membership.repository.UserRepository;
import jdk.jfr.Registered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtService jwtService;

    @Transactional
    public CommonResponse<Map> isEmailExist(String email) {
        Optional<User> optUser = userRepository.getUserByEmail(email);
        if (optUser.isPresent()) {
            return new CommonResponse<>(0, "Email sudah terdaftar", Map.of("isEmailExist", optUser.isPresent()));
        } else {
            return new CommonResponse<>(0, "Email belum terdaftar", Map.of("isEmailExist", optUser.isPresent()));
        }
    }

    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public CommonResponse<?> registration(RegistrationRequest request) {

        Optional<User> optUser = userRepository.getUserByEmail(request.getEmail());
        if (optUser.isPresent()) {
            return new CommonResponse<>(100, "Email sudah terdaftar", null);
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        return new CommonResponse<>(0, "Registrasi berhasil silahkan login", null);
    }

    @Transactional(readOnly = true)
    public CommonResponse<?> login(LoginRequest request) {
        Optional<User> optUser = userRepository.getUserByEmail(request.getEmail());
        if (optUser.isPresent()) {
            boolean checkPassword = passwordEncoder.matches(request.getPassword(), optUser.get().getPassword());
            if (checkPassword) {
                String token = jwtService.generateToken(optUser.get());
                return new CommonResponse<>(0, "Login Sukses", Map.of("token", token));
            }
        }
        return new CommonResponse<>(104, "Username atau password salah", null);
    }

}
