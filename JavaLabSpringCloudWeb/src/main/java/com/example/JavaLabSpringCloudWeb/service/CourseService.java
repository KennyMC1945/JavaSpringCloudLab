package com.example.JavaLabSpringCloudWeb.service;

import com.example.JavaLabSpringCloudWeb.model.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();
    List<Course> getCourses(List<Integer> courseIds);
    Course getCourse(int id);
}
