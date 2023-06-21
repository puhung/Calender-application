package com.example.restfulApi.calender.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@Entity
public class Task {

    public Task(){};

    public Task(Integer id, String description, LocalDate taskDate) {
        super();
        this.id = id;
        this.description = description;
        this.taskDate = taskDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String description;
    private LocalDate taskDate;
}
