package com.example.JavaLabSpringCloudWeb.repository;

import com.example.JavaLabSpringCloudWeb.model.Course;

import java.util.List;

public interface IRepository {
    List<Course> read();
    void write(List<Course> accounts);
}
