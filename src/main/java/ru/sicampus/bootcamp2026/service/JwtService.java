package ru.sicampus.bootcamp2026.service;

import ru.sicampus.bootcamp2026.dto.auth.AuthResponse;
import ru.sicampus.bootcamp2026.dto.auth.RequestLogin;
import ru.sicampus.bootcamp2026.dto.user.CreateUser;
import ru.sicampus.bootcamp2026.entity.User;

public interface JwtService {
    String createToken(User user);
    AuthResponse register(CreateUser createUser);
    AuthResponse login(RequestLogin requestLogin);
    String takeEmail(String token);

}
