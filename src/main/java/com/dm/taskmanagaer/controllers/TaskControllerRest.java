package com.dm.taskmanagaer.controllers;

import com.dm.taskmanagaer.model.Task;
import com.dm.taskmanagaer.model.User;
import com.dm.taskmanagaer.service.TaskService;
import com.dm.taskmanagaer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskControllerRest {
    private final TaskService taskService;

    @Autowired
    public TaskControllerRest(TaskService taskService, UserService userService) {
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Task getTaskById(@PathVariable("id") Long id){
        return taskService.getTaskById(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Task> getAllTask(){
        return taskService.getAllTasks();
    }

    @GetMapping("/free")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Task> getFreeTask(){
        return taskService.getFreeTasks();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public Task addTask(@RequestBody Task newTask){
        return taskService.addTask(newTask);
    }

    @PutMapping("/assign/{username}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('USER')")
    public Task assignTask(@RequestBody Task task,
                           @PathVariable("username") String username){
        return taskService.assignTaskToUser(task, username);
    }

    @PutMapping("/{id}/unassign")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('ADMIN')")
    public Task unassignTask(@PathVariable("id") Long id){
        return taskService.unassignTask(id);
    }

    @PutMapping("/{id}/complete")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('USER')")
    public Task setCompleted(@PathVariable("id") Long id){
        return taskService.setTaskCompleted(id);
    }

    @PutMapping("/{id}/unComplete")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('USER')")
    public Task setNotCompleted(@PathVariable("id") Long id){
        return taskService.setTaskNotCompleted(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteTask(@PathVariable("id") Long id){
        taskService.deleteTask(id);
    }
}
