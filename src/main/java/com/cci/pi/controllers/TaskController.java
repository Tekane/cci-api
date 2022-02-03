package com.cci.pi.controllers;

import com.cci.pi.model.Task;
import com.cci.pi.service.TaskService;
import com.cci.pi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/user/{id}/task")
    public ResponseEntity<Void> createTask(@PathVariable long id, @RequestBody Task task) {
        taskService.createTask(id, task);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //This method can be used to create a task but not assign a user to it
    @PostMapping("/user/task")
    public ResponseEntity<Void> createTask(@RequestBody Task task) {
        taskService.createTask(task);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/user/{user_id}/task/{task_id}")
    public ResponseEntity<Void> updateTask(@PathVariable("user_id") long userId, @RequestBody Task updateTask,
                                           @PathVariable("task_id") long taskId) {
        taskService.updateTask(userId, updateTask, taskId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/user/{user_id}/task/{task_id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("user_id") long userId, @PathVariable("task_id") long taskId) {
        taskService.deleteTask(userId, taskId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/user/{user_id}/task/{task_id}")
    public ResponseEntity<Task> getTaskInfo(@PathVariable("user_id") long userId, @PathVariable("task_id") long taskId) {
        Task task = taskService.getTask(userId, taskId);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping("/user/{user_id}/task")
    public ResponseEntity<List<Task>> getAllTaskForUser(@PathVariable("user_id")long taskId) {
        List<Task> tasks = taskService.getAllTaskForUser(taskId);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

}
