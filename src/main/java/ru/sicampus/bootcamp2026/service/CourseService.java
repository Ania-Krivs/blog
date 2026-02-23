package ru.sicampus.bootcamp2026.service;

import ru.sicampus.bootcamp2026.dto.CourseRequest;
import ru.sicampus.bootcamp2026.dto.user.UserCreateComment;
import ru.sicampus.bootcamp2026.entity.Comment;
import ru.sicampus.bootcamp2026.entity.Course;

import java.util.List;

public interface CourseService {
    Course createCourse(CourseRequest course);

    Comment createComment(String email, UserCreateComment createComment);

    List<Comment> getAllComments(String email);
}
