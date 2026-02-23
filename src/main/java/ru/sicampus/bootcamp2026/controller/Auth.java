package ru.sicampus.bootcamp2026.controller;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;
import ru.sicampus.bootcamp2026.dto.auth.AuthResponse;
import ru.sicampus.bootcamp2026.dto.auth.RequestLogin;
import ru.sicampus.bootcamp2026.dto.user.CreateUser;
import ru.sicampus.bootcamp2026.service.JwtService;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class Auth {

    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody @Valid CreateUser createUser){
        return ResponseEntity.status(HttpStatus.CREATED).body(jwtService.register(createUser));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid RequestLogin requestLogin){
        return ResponseEntity.ok(jwtService.login(requestLogin));
    }
}
