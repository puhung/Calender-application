package com.example.restfulApi.calender.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import com.example.restfulApi.calender.entities.Task;

@Data
@Entity
public class User {
    public User(){};

    public User(Integer id, String name, List<Task> tasks) {
        super();
        this.id = id;
        this.username = name;
        this.tasks = tasks;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;

    @OneToMany(mappedBy = "user", targetEntity = Task.class)
    private List<Task> tasks;
}
