package com.dm.taskmanagaer.service;

import com.dm.taskmanagaer.model.User;
import com.dm.taskmanagaer.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Test
    void createUser() {
        User user = new User();

    }

    @Test
    void readUserByEmail() {
    }

    @Test
    void readUserById() {
    }

    @Test
    void readAllUser() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}