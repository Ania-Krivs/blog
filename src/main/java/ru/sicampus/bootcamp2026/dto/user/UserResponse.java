package ru.sicampus.bootcamp2026.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sicampus.bootcamp2026.entity.Comment;
import ru.sicampus.bootcamp2026.entity.Course;
import ru.sicampus.bootcamp2026.entity.enums.UserRole;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String email;
    private String name;
    private LocalDate date_of_birth;
    private UserRole role;
    private Set<Course> courses;
    private List<Comment> comments;
}
