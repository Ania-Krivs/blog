package ru.sicampus.bootcamp2026.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import ru.sicampus.bootcamp2026.dto.user.UserResponse;
import ru.sicampus.bootcamp2026.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    @EntityGraph(attributePaths = {"courses", "comments"})
    Page<UserResponse> getAllUsers(Pageable pageable);

    void deleteUser(String id);

//    UserResponse createUserComment(String id);
    Map<String, List<String>> getIdCommentsByYear();

}
