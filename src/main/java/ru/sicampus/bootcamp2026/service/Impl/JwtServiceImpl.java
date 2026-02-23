package ru.sicampus.bootcamp2026.service.Impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sicampus.bootcamp2026.dto.auth.AuthResponse;
import ru.sicampus.bootcamp2026.dto.auth.RequestLogin;
import ru.sicampus.bootcamp2026.dto.user.CreateUser;
import ru.sicampus.bootcamp2026.entity.User;
import ru.sicampus.bootcamp2026.entity.enums.UserRole;
import ru.sicampus.bootcamp2026.exceptions.UserNotAuthorizedException;
import ru.sicampus.bootcamp2026.exceptions.UserNotFoundError;
import ru.sicampus.bootcamp2026.repository.UserRepository;
import ru.sicampus.bootcamp2026.service.JwtService;

import java.util.Date;

@Builder
@Service
public class JwtServiceImpl implements JwtService {
    private final String  SECRET;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public JwtServiceImpl(@Value("${jwt.secret}") String SECRET,
                          UserRepository userRepository,
                          PasswordEncoder encoder){
        this.SECRET = SECRET;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }



    @Override
    public String createToken(User user){
        Date now = new Date();
        Date exp = new Date(now.getTime() + 3600_000);

        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole().name())
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();

    }

    @Override
    public AuthResponse register(CreateUser createUser){
        User user = userRepository.findByEmail(createUser.getEmail());
        if (user != null){
            throw new UserNotFoundError("Пользователь уже существует");
        }

        User user1 = User.builder()
                .email(createUser.getEmail())
                .name(createUser.getName())
                .date_of_birth(createUser.getDate_of_birth())
                .hashPassword(encoder.encode(createUser.getPassword()))
                .role(UserRole.USER)
                .build();
        userRepository.save(user1);

        String token = createToken(user1);
        return new AuthResponse(
                token,
                3600
        );

    }

    @Override
    public AuthResponse login(RequestLogin requestLogin){
        User user = userRepository.findByEmail(requestLogin.getEmail());
        if (user == null || encoder.matches(requestLogin.getPassword(), encoder.encode(user.getHashPassword()))){
            throw new UserNotAuthorizedException("Неверная почта или пароль");
        }
        String token = createToken(user);
        return new AuthResponse(token, 3600_000);
    }

    @Override
    public String takeEmail(String token){
        return Jwts.parser()
                .setSigningKey(SECRET)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
