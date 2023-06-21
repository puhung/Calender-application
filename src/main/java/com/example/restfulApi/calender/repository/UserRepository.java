package com.example.restfulApi.calender.repository;

import com.example.restfulApi.calender.entities.Task;
import com.example.restfulApi.calender.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
