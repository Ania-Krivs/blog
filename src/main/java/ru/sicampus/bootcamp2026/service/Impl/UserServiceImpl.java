package ru.sicampus.bootcamp2026.service.Impl;

import liquibase.ui.UIServiceFactory;
import lombok.Builder;
import org.hibernate.annotations.Comments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.sicampus.bootcamp2026.dto.user.UserResponse;
import ru.sicampus.bootcamp2026.entity.Comment;
import ru.sicampus.bootcamp2026.entity.User;
import ru.sicampus.bootcamp2026.exceptions.UserNotFoundError;
import ru.sicampus.bootcamp2026.repository.CommentRepository;
import ru.sicampus.bootcamp2026.repository.UserRepository;
import ru.sicampus.bootcamp2026.service.UserService;

import java.lang.reflect.Array;
import java.util.*;

@Builder
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Override
    public Page<UserResponse> getAllUsers(Pageable pageable){
        return userRepository.findAll(pageable)
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getEmail(),
                        user.getName(),
                        user.getDate_of_birth(),
                        user.getRole(),
                        user.getCourses(),
                        user.getComments()
                ));
    }

    @Override
    public void deleteUser(String id){
        Optional<User> user = userRepository.findById(Long.parseLong(id));
        if (user.isEmpty()){
            throw new UserNotFoundError("Пользователь не найден");
        }

        userRepository.delete(user.get());
        return;
    }

//    @Override
//    public UserResponse createUserComment(String id, UserCreateComment userCreateComment){
//        Optional<User> user = userRepository.findById(Long.parseLong(id));
//        if (user.isEmpty()){
//            throw new UserNotFoundError("");
//        }
//        if(userCreateComment.getName() != null){
//            user.get().setName(userCreateComment.getName());
//        }
//        if(userCreateComment.getContent() != null){
//            Comment comment = Comment.builder()
//                    .user(user.get())
//                    .content(userCreateComment.getContent())
//                    .build();
//            user.get().setName(userCreateComment.getName());
//        }
//
//
//
//    }

    @Override
    public Map<String, List<String>> getIdCommentsByYear(){
        List<Comment> comments = commentRepository.findAll();
        Map<String, List<String>> mapDate = new HashMap<>();

        for (Comment comment : comments){
            String year = String.valueOf(comment.getUser().getDate_of_birth().getYear());
            mapDate.computeIfAbsent(year, y -> new ArrayList<>()).add(String.valueOf(comment.getId()));
        }

        return mapDate;

    }
}
