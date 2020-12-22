package com.example.JavaLabSpringCloudWeb.model;

import java.util.List;

public class Store {
    public List<Course> courseList;

    public Store(List<Course> courseList) {
        this.courseList = courseList;
    }
}
