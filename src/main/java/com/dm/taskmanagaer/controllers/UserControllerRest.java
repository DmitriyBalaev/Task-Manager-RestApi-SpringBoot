package com.dm.taskmanagaer.controllers;

import com.dm.taskmanagaer.model.User;
import com.dm.taskmanagaer.service.TaskService;
import com.dm.taskmanagaer.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserControllerRest {
    private final UserService userService;
    private final TaskService taskService;

    public UserControllerRest(UserService userServicel, TaskService taskService) {
        this.userService = userServicel;
        this.taskService = taskService;
    }

    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public User getUser(@PathVariable("username") String username){
        return userService.readUserByName(username);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<User> getAllUsers(){
        return userService.readAllUser();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody User user){
        return userService.add(user);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public User updateUser(User user){
        return userService.update(user);
    }

    @PutMapping("/{username}/giveAdmin")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('ADMIN')")
    public User giveAdminRole(@PathVariable("username") String username){
        return userService.giveAdmin(username);
    }

    @PutMapping("/{username}/takeAdmin")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('ADMIN')")
    public User takeAdminRole(@PathVariable("username") String username){
        return userService.removeAdmin(username);
    }

    @DeleteMapping("/{username}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable("username") String name){
        userService.delete(name);
    }

}
