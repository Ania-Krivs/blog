package ru.sicampus.bootcamp2026.dto.user;


import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sicampus.bootcamp2026.entity.enums.UserRole;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUser {
    @Email
    private String email;
    private String name;
    private LocalDate date_of_birth;
    private String password;
}
