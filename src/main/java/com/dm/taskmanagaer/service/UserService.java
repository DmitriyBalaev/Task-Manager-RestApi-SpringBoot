package com.dm.taskmanagaer.service;

import com.dm.taskmanagaer.model.User;

import java.util.List;

public interface UserService {

    User add(User user);

    User readUserByEmail(String email);

    User readUserByName(String name);

    List<User> readAllUser();

    User update(User user);

    void delete(String name);

    User giveAdmin(String name);

    User removeAdmin(String name);


}
