package com.example.JavaLabSpringCloudWeb.service;

import com.example.JavaLabSpringCloudWeb.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;

public class MongoCourseService implements CourseService {
    @Autowired
    MongoTemplate mongoTemplate;
    @Override
    public List<Course> getAllCourses() {
        return mongoTemplate.findAll(Course.class);
    }

    @Override
    public List<Course> getCourses(List<Integer> courseIds) {
        List<Course> courses = new ArrayList<>();
        for (Integer i: courseIds){
            courses.add(getCourse(i));
        }
        return courses;
    }

    @Override
    public Course getCourse(int id) {
        Course course = mongoTemplate.query(Course.class).matching(Query.query(Criteria.where("id").is(id))).oneValue();
        return course;
    }
}
