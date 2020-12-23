package com.example.JavaLabSpringCloudWeb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
@Document(collection = "courses")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Course {

    @Field("title")
    private String title;
    @Field("author")
    private String author;
    @Field("id")
    private int id;
    @Field("tags")
    private List<String> tags;
    @Field("lessons")
    private List<String> lessons;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getLessons() {
        return lessons;
    }

    @Override
    public String toString() {
        return "Course '" + title + "\'\n" +
                "Author: " + author + '\n' +
                "Tags: " + tags +
                "\nLessons: " + lessons +
                '\n';
    }

    public String briefDescription(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getId());
        stringBuilder.append(" : ");
        stringBuilder.append(getTitle());
        stringBuilder.append(" Author: ");
        stringBuilder.append(getAuthor());
        stringBuilder.append(" Tags: ");
        stringBuilder.append(getTags());
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    public void setLessons(List<String> lessons) {
        this.lessons = lessons;
    }
}
