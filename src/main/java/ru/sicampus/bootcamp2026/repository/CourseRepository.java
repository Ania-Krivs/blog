package ru.sicampus.bootcamp2026.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sicampus.bootcamp2026.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByName(String name);
}
