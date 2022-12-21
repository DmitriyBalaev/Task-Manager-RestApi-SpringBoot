package com.dm.taskmanagaer.dataloader;

import com.dm.taskmanagaer.model.User;
import com.dm.taskmanagaer.model.role.Role;
import com.dm.taskmanagaer.repository.RoleRepository;
import com.dm.taskmanagaer.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.dm.taskmanagaer.model.role.RoleType.*;

@Component
public class InitialData {

    @Bean
    public CommandLineRunner dataLoader(RoleRepository roleRepository,
                                        UserService userService){
        return args -> {
            roleRepository.save(new Role(ROLE_USER.name()));
            roleRepository.save(new Role(ROLE_ADMIN.name()));

            User admin = new User(
                    "user",
                    "email.com",
                    "password",
                    new ArrayList<Role>(List.of(
                            new Role(ROLE_ADMIN.name()),
                            new Role(ROLE_USER.name())))
            );
            userService.add(admin);

        };
    }
}
