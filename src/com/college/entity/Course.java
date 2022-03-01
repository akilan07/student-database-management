package com.college.entity;

public class Course {
    private String name;
    private long id;
    private int price;

    public Course() {}

    public Course(String name, long id) {
        this.name = name;
        this.id = id;
        this.price = 600;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }
}
