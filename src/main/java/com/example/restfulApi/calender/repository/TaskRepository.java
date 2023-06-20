package com.example.restfulApi.calender.repository;

import com.example.restfulApi.calender.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByUsername(String username);
}
