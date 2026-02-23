package ru.sicampus.bootcamp2026.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sicampus.bootcamp2026.entity.Course;
import ru.sicampus.bootcamp2026.entity.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    private String id;
    private String content;
    private User user;
    private Course course;
}
