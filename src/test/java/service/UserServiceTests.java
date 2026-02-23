package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.sicampus.bootcamp2026.dto.user.UserResponse;
import ru.sicampus.bootcamp2026.entity.User;
import ru.sicampus.bootcamp2026.entity.enums.UserRole;
import ru.sicampus.bootcamp2026.repository.UserRepository;
import ru.sicampus.bootcamp2026.service.UserService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(classes = ru.sicampus.bootcamp2026.App.class)
@Transactional
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setName("Иван Иванов");
        testUser.setEmail("ivan@example.com");
        testUser.setDate_of_birth(LocalDate.of(1990, 1, 1));
        testUser.setHashPassword("$10$z9ygGiEhv8sZKwkoaErgo.PdoyjlXiFaOiH4.VaPZPq.niss.6xDe");
        testUser.setRole(UserRole.USER);
        userRepository.save(testUser);
    }

    @Test
    void testGetAllUsers() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<UserResponse> usersPage = userService.getAllUsers(pageable);

        assertNotNull(usersPage);
        assertFalse(usersPage.isEmpty());

        boolean found = usersPage.stream()
                .anyMatch(u -> u.getEmail().equals("ivan@example.com") &&
                        u.getName().equals("Иван Иванов"));

        assertTrue(found);
        assertEquals(1, usersPage.getTotalPages());
        assertEquals(2, usersPage.getTotalElements());
    }
}