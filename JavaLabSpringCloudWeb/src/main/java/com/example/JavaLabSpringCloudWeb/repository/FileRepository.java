package com.example.JavaLabSpringCloudWeb.repository;

import com.example.JavaLabSpringCloudWeb.model.Course;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileRepository implements IRepository {
    private static final String DB_FILENAME = "courses.json";


    public List<Course> createDBFile() throws IOException {
        File db = new File(DB_FILENAME);
        ObjectMapper mapper = new ObjectMapper();
        db.createNewFile();
        List<Course> courses = new ArrayList<>();
        FileWriter fw = new FileWriter(db,false);
        Course c = new Course();
        ArrayList<String> tags = new ArrayList<>();
        ArrayList<String> lessons = new ArrayList<>();
        c.setAuthor("Adam Dantiego");
        c.setId(0);
        c.setTitle("Be better with your IDE!");
        tags.add("Programming"); tags.add("Efficiency");
        lessons.add("Lesson 1. Use shortkeys");
        lessons.add("Lesson 2. Be efficient");
        c.setTags(tags);
        c.setLessons(lessons);
        courses.add(c);
        c = new Course();
        tags = new ArrayList<>();
        lessons = new ArrayList<>();
        c.setAuthor("Santiago Delevin");
        c.setId(1);
        c.setTitle("How to create stunning visuals");
        tags.clear();
        lessons.clear();
        tags.add("Cinema"); tags.add("Visual Production"); tags.add("Post-processing");
        lessons.add("Lesson 1. Use Adobe Premier");
        lessons.add("Lesson 2. Use Adobe After Effects");
        lessons.add("Lesson 3. Try things");
        c.setTags(tags);
        c.setLessons(lessons);
        courses.add(c);
        mapper.writeValue(fw, courses);
        return courses;
    }

    @Override
    public List<Course> read() {
        File db = new File(DB_FILENAME);
        try {
            if (!db.exists()) {
                return createDBFile();
            } else {
                ObjectMapper mapper = new ObjectMapper();
                List<Course> courses = mapper.readValue(db, new TypeReference<ArrayList<Course>>() {});
                return courses;
            }
        } catch (IOException e) {
            System.out.println("ERROR: Troubles with json file");
            return null;
        }

    }

    @Override
    public void write(List<Course> accounts) {
        try {
            File db = new File(DB_FILENAME);
            ObjectMapper mapper = new ObjectMapper();
            FileWriter fw = new FileWriter(db, false);
            mapper.writeValue(fw, accounts);
        } catch (IOException e) {
            System.out.println("ERROR: Writing to JSON");
        }
    }
}
