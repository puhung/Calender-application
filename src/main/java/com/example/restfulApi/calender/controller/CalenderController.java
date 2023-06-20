package com.example.restfulApi.calender.controller;

import com.example.restfulApi.calender.entities.Task;
import com.example.restfulApi.calender.repository.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CalenderController {

    private TaskRepository taskRepository;
    public CalenderController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/user/{username}/tasks")
    public List<Task> getAllTasksByUsername(@PathVariable String username){
        return taskRepository.findByUsername(username);
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
        task.setUsername(username);
        task.setId(null);
        taskRepository.save(task);
       return ResponseEntity.ok(task);
    }

    @DeleteMapping("/user/tasks/{id}")
    public String deleteTaskById(@PathVariable Integer id){
        taskRepository.deleteById(id);
        return "Successfully delete the Task with id:"+id+".";
    }

    @DeleteMapping("/user/tasks")
    public String deleteAllTasks(){
        taskRepository.deleteAllInBatch();
        return "Successfully deleted all tasks";
    }
}
