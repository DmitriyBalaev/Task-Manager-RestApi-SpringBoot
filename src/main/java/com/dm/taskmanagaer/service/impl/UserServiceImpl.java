package com.dm.taskmanagaer.service.impl;

import com.dm.taskmanagaer.exceptions.ApplicationException;
import com.dm.taskmanagaer.exceptions.BadRequestException;
import com.dm.taskmanagaer.exceptions.NotFoundResource;
import com.dm.taskmanagaer.model.User;
import com.dm.taskmanagaer.model.role.Role;
import com.dm.taskmanagaer.model.role.RoleType;
import com.dm.taskmanagaer.repository.RoleRepository;
import com.dm.taskmanagaer.repository.UserRepository;
import com.dm.taskmanagaer.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User add(User user) {
        if (userRepository.existsByName(user.getName())){
            throw new BadRequestException("Name is already taken.");
        }
        if (userRepository.existsByEmail(user.getEmail())){
            throw new BadRequestException("Email is already taken.");
        }

        Role userRole = getRoleUser();
        user.setRoles(List.of(userRole));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User readUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new NotFoundResource("User with email:" + email +" does not exist."));
    }

    @Override
    public User readUserByName(String name) {
        return userRepository.findByName(name)
                .orElseThrow(()-> new BadRequestException("User with name:" + name + " does not exist."));
    }

    @Override
    public List<User> readAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User update(User userToUpdate) {
        User currentUser = readUserByName(userToUpdate.getName());
        currentUser.setPassword(passwordEncoder.encode(userToUpdate.getPassword()));

        return userRepository.save(currentUser);
    }

    @Override
    public void delete(String name) {
        User currentUser = readUserByName(name);
        userRepository.delete(currentUser);
    }

    @Override
    public User giveAdmin(String name) {
        User currentUser = readUserByName(name);
        Role userRole = getRoleUser();
        Role adminRole = getRoleAdmin();

        currentUser.setRoles(List.of(userRole, adminRole));

        return userRepository.save(currentUser);
    }

    @Override
    public User removeAdmin(String name) {
        User currentUser = readUserByName(name);
        Role userRole = getRoleUser();

        currentUser.setRoles(List.of(userRole));

        return userRepository.save(currentUser);
    }

    private Role getRoleUser(){
        return roleRepository.findByRole(RoleType.ROLE_USER.name())
                .orElseThrow(()-> new ApplicationException("Role not set for user"));
    }

    private Role getRoleAdmin(){
        return roleRepository.findByRole(RoleType.ROLE_ADMIN.name())
                .orElseThrow(()-> new ApplicationException("Role not set for user"));
    }

}
