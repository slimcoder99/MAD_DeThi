package com.example.quanlyhocsinh.model;

import java.io.Serializable;

public class StatisticClass implements Serializable {
    private int id;
    private String name;
    private String description;
    private int number_student;

    public StatisticClass() {
    }

    public StatisticClass(int id, String name, String description, int number_student) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.number_student = number_student;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumber_student() {
        return number_student;
    }

    public void setNumber_student(int number_student) {
        this.number_student = number_student;
    }
}
