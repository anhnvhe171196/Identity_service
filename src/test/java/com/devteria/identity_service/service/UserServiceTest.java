package com.devteria.identity_service.service;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import com.devteria.identity_service.dto.reponse.UserResponse;
import com.devteria.identity_service.dto.request.UserCreationRequest;
import com.devteria.identity_service.entity.User;
import com.devteria.identity_service.exception.AppException;
import com.devteria.identity_service.repository.UserRepository;

@SpringBootTest
@TestPropertySource("/test.properties")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private UserCreationRequest request;
    private UserResponse response;
    private User user;
    private LocalDate dob;

    @BeforeEach
    void initData() {
        dob = LocalDate.of(2003, 11, 28);
        request = UserCreationRequest.builder()
                .username("john")
                .firstName("John")
                .lastName("Doe")
                .password("12345678")
                .dob(dob)
                .build();
        response = UserResponse.builder()
                .id("5bc8540b3081")
                .username("john")
                .firstName("John")
                .lastName("Doe")
                .dob(dob)
                .build();
        user = User.builder()
                .id("5bc8540b3081")
                .username("john")
                .firstName("John")
                .lastName("Doe")
                .dob(dob)
                .build();
    }

    @Test
    void createUser_validRequest_success() {
        // GIVEN
        Mockito.when(userRepository.existsByUsername(request.getUsername())).thenReturn(false);
        Mockito.when(userRepository.save(ArgumentMatchers.any())).thenReturn(user);

        // WHEN
        var userResponse = userService.createUser(request);

        // THEN
        Assertions.assertThat(userResponse.getId()).isEqualTo("5bc8540b3081");
        Assertions.assertThat(userResponse.getUsername()).isEqualTo("john");
    }

    @Test
    void createUser_userExisted_fail() {
        // GIVEN
        Mockito.when(userRepository.existsByUsername(request.getUsername())).thenReturn(true);

        // WHEN
        var exception = org.junit.jupiter.api.Assertions.assertThrows(
                AppException.class, () -> userService.createUser(request));

        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1001);
    }
}
