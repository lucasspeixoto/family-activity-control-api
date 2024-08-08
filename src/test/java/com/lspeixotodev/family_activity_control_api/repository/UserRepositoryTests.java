package com.lspeixotodev.family_activity_control_api.repository;

import com.lspeixotodev.family_activity_control_api.entity.authentication.User;
import com.lspeixotodev.family_activity_control_api.integrationtests.AbstractIntegrationTest;
import com.lspeixotodev.family_activity_control_api.repository.authentication.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("it")
@DisplayName("User Repository (Integration Tests)")
public class UserRepositoryTests extends AbstractIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("User Repository: Find User By Username Or Email then Returns User")
    @Order(1)
    public void userRepository_findUserByUsernameOrEmail_ReturnsUser() {
        Optional<User> optionalUser = userRepository.findUserByUsernameOrEmail(
                "lucas", "lspeixotodev@gmail.com"
        );

        assertThat(optionalUser).isPresent();
        assertThat(optionalUser.get().getUsername()).isEqualTo("lucas");
        assertThat(optionalUser.get().getEmail()).isEqualTo("lspeixotodev@gmail.com");
        assertThat(optionalUser.get().getName()).isEqualTo("Lucas Peixoto");
    }

    @Test
    @DisplayName("User Repository: Existing by username then Returns True")
    @Order(2)
    public void userRepository_existsByUsername_ReturnsTrue() {
        boolean existsByUsername = userRepository.existsByUsername("lucas");
        assertThat(existsByUsername).isTrue();
    }

    @Test
    @DisplayName("User Repository: Existing by username then Returns False")
    @Order(3)
    public void userRepository_existsByUsername_ReturnsFalse() {
        boolean existsByUsername = userRepository.existsByUsername("nonExistingUsername");
        assertThat(existsByUsername).isFalse();
    }

    @Test
    @DisplayName("User Repository: Existing by email then Returns True")
    @Order(4)
    public void userRepository_existsEmail_ReturnsTrue() {
        boolean existsByName = userRepository.existsByEmail("lspeixotodev@gmail.com");
        assertThat(existsByName).isTrue();
    }

    @Test
    @DisplayName("User Repository: Existing by email then Returns False")
    @Order(5)
    public void userRepository_existsByEmail_ReturnsFalse() {
        boolean existsByName = userRepository.existsByEmail("nonExistingEmail");
        assertThat(existsByName).isFalse();
    }
}
