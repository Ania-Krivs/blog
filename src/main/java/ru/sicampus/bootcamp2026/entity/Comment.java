package ru.sicampus.bootcamp2026.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Table(name = "comment")
@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class    Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties(value = {"courses", "comments"})
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIgnoreProperties(value = {"users", "comments"})
    private Course course;
}
