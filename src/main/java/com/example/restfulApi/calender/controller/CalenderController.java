package com.example.restfulApi.calender.controller;

import com.example.restfulApi.calender.entities.Task;
import com.example.restfulApi.calender.entities.User;
import com.example.restfulApi.calender.repository.TaskRepository;
import com.example.restfulApi.calender.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CalenderController {

    private TaskRepository taskRepository;
    private UserRepository userRepository;
    public CalenderController(TaskRepository taskRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @GetMapping("/user/{username}/tasks")
    public List<Task> getAllTasksByUsername(@PathVariable String username){
        return userRepository.findByUsername(username).getTasks();
    }

    @GetMapping("/user/tasks")
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    @GetMapping("/user/tasks/{id}")
    public Task getTaskbyId(@PathVariable Integer id){
        return taskRepository.findById(id).get();
    }

    @PostMapping("/user/{username}/tasks")
    public ResponseEntity<Task> uploadTask(@PathVariable String username, @RequestBody Task task){
        User user = userRepository.findByUsername(username);
        if(user == null){
            user = new User(null, username, new ArrayList<>());
        }

        user.getTasks().add(task);
        task.setId(null);
        task.setUser(user);
        userRepository.save(user);
        taskRepository.save(task);
       return ResponseEntity.ok(task);
    }

    @DeleteMapping("/user/tasks/{id}")
    public ResponseEntity<String> deleteTaskById(@PathVariable Integer id){
        Task task = taskRepository.findById(id).get();
        if (task != null){
            Integer user_id = task.getUser().getId();
            User user = userRepository.findById(user_id).get();
            List<Task> tasks = user.getTasks();
            tasks.remove(task);
            userRepository.save(user);
            taskRepository.delete(task);
            return ResponseEntity.ok("Successfully delete the Task with id:"+id+".");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/user/tasks")
    public ResponseEntity<String> deleteAllTasks(){
        List<User> users = userRepository.findAll();
        for(User user: users){
            user.setTasks(new ArrayList<>());
            userRepository.save(user);
        }
        taskRepository.deleteAllInBatch();
        return ResponseEntity.ok("Successfully deleted all tasks");
    }
}
