package com.cci.pi;

import com.cci.pi.model.Task;
import com.cci.pi.model.User;
import com.cci.pi.service.TaskService;
import com.cci.pi.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
class CciApiApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @Test
    public void saveUser() {
        User newUser = new User();
        newUser.setUsername("jsmith");
        newUser.setFirst_name("John");
        newUser.setLast_name("Smith");
        userService.createUser(newUser);
    }

    @Test
    public void updateUser() throws Exception {
        User userUpDate = new User();
        userUpDate.setFirst_name("John");
        userUpDate.setLast_name("Doe");
        userService.updateUser(userUpDate, 1);
    }

    @Test
    public void listAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users != null) {
            for (User user : users) {
                System.out.println(user.getLast_name());
            }
        }
    }

    @Test
    public void getUserById() throws Exception {
        System.out.println(userService.getUserById(1));
    }

    //Now testing creating task functionality

    @Test
    public void createTask() {
        Task task = new Task();
        task.setName("My task");
        task.setDescription("Description of task");
        task.setStatus("In Progress");
        task.setDate_time("2016-05-25 14:25:00");
        taskService.createTask(1, task);
    }

    @Test
    public void updateTask() {
        Task task = new Task();
        task.setName("My updated task final");
        taskService.updateTask(1, task, 1);
    }

    @Test
    public void deleteTask() {
        taskService.deleteTask(1, 1);
    }

    @Test
    public void getTaskInfo() {
        Task task = taskService.getTask(1, 2);
        System.out.println(task.getName());
        System.out.println(task.getDate_time());
        System.out.println("This task is assigned to " + task.getUser().getUsername());
    }

    @Test
    public void allTasksForUser() {
        List<Task> allTaskForUser = taskService.getAllTaskForUser(1);
        for (Task task : allTaskForUser) {
            System.out.println(task.getName());
            System.out.println(task.getDate_time());
        }
    }

}
