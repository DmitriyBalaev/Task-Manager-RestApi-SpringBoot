package com.dm.taskmanagaer.service;

import com.dm.taskmanagaer.model.Task;
import com.dm.taskmanagaer.model.User;

import java.util.List;

public interface TaskService {

    Task addTask(Task task);

    Task getTaskById(Long id);

    Task updateTask(Long taskId,Task task);

    void deleteTask(Long id);

    List<Task> getAllTasks();

    List<Task> getFreeTasks();

    Task setTaskCompleted(Long id);

    Task setTaskNotCompleted(Long id);

    Task assignTaskToUser(Task task, String username);

    Task unassignTask(Long id);
}
