package ru.sicampus.bootcamp2026.service.Impl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sicampus.bootcamp2026.dto.CourseRequest;
import ru.sicampus.bootcamp2026.dto.user.UserCreateComment;
import ru.sicampus.bootcamp2026.entity.Comment;
import ru.sicampus.bootcamp2026.entity.Course;
import ru.sicampus.bootcamp2026.entity.User;
import ru.sicampus.bootcamp2026.exceptions.UserNotFoundError;
import ru.sicampus.bootcamp2026.repository.CommentRepository;
import ru.sicampus.bootcamp2026.repository.CourseRepository;
import ru.sicampus.bootcamp2026.repository.UserRepository;
import ru.sicampus.bootcamp2026.service.CourseService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Builder
public class CourseServiseImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Override
    public Course createCourse(CourseRequest course) {

        if (courseRepository.existsByName(course.getName())) {
            throw new IllegalArgumentException("Course with this name already exists");
        }

        Course course1 = Course.builder()
                .name(course.getName())
                .build();

        return courseRepository.save(course1);
    }

    @Override
    public Comment createComment(String email, UserCreateComment userCreateComment){
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new UserNotFoundError("Пользователь не найден");
        }

        Comment comment = Comment.builder()
                .content(userCreateComment.getContent())
                .user(user)
                .course(courseRepository.findById(Long.parseLong(userCreateComment.getCourse_id())).get())
                .build();

        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getAllComments(String email){
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new UserNotFoundError("Пользователь не найден");
        }

        return user.getComments().stream()
                .map(comment -> new Comment(
                        comment.getId(),
                        comment.getContent(),
                        comment.getUser(),
                        comment.getCourse()
                ))
                .toList();
    }
}
