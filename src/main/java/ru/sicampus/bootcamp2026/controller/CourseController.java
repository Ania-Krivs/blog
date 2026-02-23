package ru.sicampus.bootcamp2026.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.sicampus.bootcamp2026.dto.CourseRequest;
import ru.sicampus.bootcamp2026.dto.user.UserCreateComment;
import ru.sicampus.bootcamp2026.entity.Comment;
import ru.sicampus.bootcamp2026.entity.Course;
import ru.sicampus.bootcamp2026.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody CourseRequest course) {
        Course savedCourse = courseService.createCourse(course);
        return ResponseEntity.ok(savedCourse);
    }

    @PostMapping("/comment")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Comment> createComment(
            @AuthenticationPrincipal String email,
            @RequestBody UserCreateComment userCreateComment
            ){
        return ResponseEntity.ok(courseService.createComment(email, userCreateComment));
    }

    @GetMapping("/comments")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<List<Comment>> getAllCommet(
            @AuthenticationPrincipal String email
    ){
        return ResponseEntity.ok(courseService.getAllComments(email));
    }
}
