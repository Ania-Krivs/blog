package ru.sicampus.bootcamp2026.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.sicampus.bootcamp2026.dto.user.UserResponse;
import ru.sicampus.bootcamp2026.entity.Comment;
import ru.sicampus.bootcamp2026.service.UserService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class User {

    private final UserService userService;

    @GetMapping("")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Page<UserResponse>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(userService.getAllUsers(pageable));
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<?> deleteUser(
            @PathVariable String id
    ){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/comments_by_date")
    public ResponseEntity<Map<String, List<String>>> getIdCommentsByYear(){
        return ResponseEntity.ok(userService.getIdCommentsByYear());
    }


//    @PatchMapping("/{id}")
//    public ResponseEntity<UserResponse> createUserComment(
//            @PathVariable String id
//    ){
//        return;
//    }
}
