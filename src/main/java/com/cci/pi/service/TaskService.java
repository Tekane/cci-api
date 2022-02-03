package com.cci.pi.service;

import com.cci.pi.error.ResourceNotFoundException;
import com.cci.pi.model.Task;
import com.cci.pi.model.User;
import com.cci.pi.repository.TaskRepository;
import com.cci.pi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void createTask(long userId, Task task) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            task.setUser(user.get());
            task.setDescription(task.getDescription());
            task.setStatus(task.getStatus() != null ? task.getStatus() : "pending");
            //I think this should just use the current date new Date();
            task.setDate_time(task.getDate_time());
            taskRepository.save(task);
        }
    }

    @Transactional
    public void createTask(Task task) {
        task.setUser(new User());
        task.setDescription(task.getDescription());
        task.setDate_time(task.getDate_time());
        taskRepository.save(task);
    }

    @Transactional
    public void updateTask(long userId, Task updateTask, long taskId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            Optional<Task> task = taskRepository.findByUserAndTask(user.get(), taskId);
            if (task.isPresent()) {
                task.get().setName(updateTask.getName());
                taskRepository.save(task.get());
            } else {
                throw new ResourceNotFoundException();
            }
        }
    }

    @Transactional
    public void deleteTask(long userId, long taskId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            Optional<Task> task = taskRepository.findByUserAndTask(user.get(), taskId);
            if (task.isPresent()) {
                taskRepository.delete(task.get());
            } else {
                throw new ResourceNotFoundException();
            }
        }
    }

    @Transactional
    public Task getTask(long userId, long taskId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            Optional<Task> task = taskRepository.findByUserAndTask(user.get(), taskId);
            if (task.isPresent()) {
                return task.get();
            } else {
                throw new ResourceNotFoundException();
            }
        }
        return null;
    }

    @Transactional
    public List<Task> getAllTaskForUser(long taskId) {
        Optional<User> user = userRepository.findById(taskId);
        if (user.isPresent()) {
            return taskRepository.findByUser(user.get());
        }
        return null;
    }

    @Transactional
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}
