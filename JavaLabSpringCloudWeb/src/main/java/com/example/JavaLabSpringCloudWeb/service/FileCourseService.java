package com.example.JavaLabSpringCloudWeb.service;

import com.example.JavaLabSpringCloudWeb.model.Course;
import com.example.JavaLabSpringCloudWeb.model.Store;
import com.example.JavaLabSpringCloudWeb.repository.IRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class FileCourseService implements CourseService {

    @Autowired
    public Store store;

    @Autowired
    public IRepository repository;

    @Override
    public List<Course> getAllCourses() {
        return store.courseList;
    }

    @Override
    public List<Course> getCourses(List<Integer> courseIds) {
        List<Course> courses = new ArrayList<>();
        for (int i : courseIds) {
            courses.add(getCourse(i));
        }
        return courses;
    }

    @Override
    public Course getCourse(int id) {
        for (Course c: store.courseList){
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }





}