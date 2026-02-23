package ru.sicampus.bootcamp2026.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateComment {
//    private String user_id;
    private String course_id;
    private String name;
    private String content;
}
