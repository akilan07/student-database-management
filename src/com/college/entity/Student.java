package com.college.entity;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private long id;
    private String name;
    private int year;
    private int balance;
    private List<Long> courses;

    public Student(){}

    public Student(String name, int year){
        this.name = name;
        this.year = year;
        this.balance = 5000;
        courses = new ArrayList<>();
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public int getBalance() {
        return balance;
    }

    public List<Long> getCourses() {
        return courses;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setCourses(List<Long> courses) {
        this.courses = courses;
    }
}
