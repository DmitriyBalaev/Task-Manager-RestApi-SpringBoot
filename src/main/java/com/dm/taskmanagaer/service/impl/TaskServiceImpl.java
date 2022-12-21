package com.dm.taskmanagaer.service.impl;

import com.dm.taskmanagaer.exceptions.NotFoundResource;
import com.dm.taskmanagaer.model.Task;
import com.dm.taskmanagaer.model.User;
import com.dm.taskmanagaer.repository.TaskRepository;
import com.dm.taskmanagaer.service.TaskService;
import com.dm.taskmanagaer.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;

    public TaskServiceImpl(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    @Override
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(()-> new NotFoundResource("Task not found."));
    }

    @Override
    public Task updateTask(Long id, Task task) {
        Task currentTask = getTaskById(id);

        currentTask.setName(task.getName());
        currentTask.setDescription(task.getDescription());
        currentTask.setDate(task.getDate());

        return taskRepository.save(currentTask);
    }

    @Override
    public void deleteTask(Long id) {
        Task currentTask = getTaskById(id);

        taskRepository.delete(currentTask);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getFreeTasks() {
        return taskRepository.findAll().stream()
                .filter(task -> task.getOwner() == null && !task.isCompleted())
                .toList();
    }

    @Override
    public Task setTaskCompleted(Long id) {
        Task currentTask = getTaskById(id);

        currentTask.setCompleted(true);

        return taskRepository.save(currentTask);
    }

    @Override
    public Task setTaskNotCompleted(Long id) {
        Task currentTask = getTaskById(id);
        currentTask.setCompleted(true);

        return taskRepository.save(currentTask);
    }


    @Override
    public Task assignTaskToUser(Task task, String username) {
        Task currentTask = getTaskById(task.getId());
        User currentUser = userService.readUserByName(username);
        currentTask.setOwner(currentUser);

        return taskRepository.save(currentTask);
    }

    @Override
    public Task unassignTask(Long id) {
        Task currentTask = getTaskById(id);
        currentTask.setOwner(null);

        return taskRepository.save(currentTask);
    }
}
