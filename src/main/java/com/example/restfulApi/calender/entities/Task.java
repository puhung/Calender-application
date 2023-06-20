package com.example.restfulApi.calender.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
public class Task {

    public Task(){

    }

    public Task(Integer id, String name, String description, LocalDate taskDate) {
        super();
        this.id = id;
        this.username = name;
        this.description = description;
        this.taskDate = taskDate;
    }

    @Id
    @GeneratedValue
    private Integer id;
    private String username;
    private String description;
    private LocalDate taskDate;
}
