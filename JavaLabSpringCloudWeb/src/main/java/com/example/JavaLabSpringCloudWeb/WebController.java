package com.example.JavaLabSpringCloudWeb;

import com.example.JavaLabSpringCloudWeb.model.Course;
import com.example.JavaLabSpringCloudWeb.service.CourseService;
import com.example.JavaLabSpringCloudWeb.service.FileCourseService;
import com.example.JavaLabSpringCloudWeb.source.Sender;
import com.example.JavaLabSpringCloudWeb.source.TestChannelSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableBinding(TestChannelSource.class)
public class WebController {

    @Autowired
    public TestChannelSource testChannelSource;

    public Integer user_id = null;

    @Autowired
    public CourseService courseService;

    @Autowired
    public Sender sender;

    @GetMapping("/")
    String index(){
        return "User /set/{name} to change username on host:7171";
    }



    @GetMapping("/course/{id}")
    public ResponseEntity<String> getCourse(@PathVariable int id) {
        return new ResponseEntity<String>(courseService.getCourse(id).toString(),null,HttpStatus.OK);
    }

    @GetMapping("/course")
    public ResponseEntity<String> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        StringBuilder stringBuilder = new StringBuilder();
        for (Course course : courses){
            stringBuilder.append(course.briefDescription());
        }
        return new ResponseEntity<>(stringBuilder.toString(), HttpStatus.OK);
    }

    @PostMapping("/course/{id}/subscribe")
    public ResponseEntity<String> subscribe(@PathVariable int id) {
        if (user_id == null) return new ResponseEntity<>("You're not signed in!", HttpStatus.OK);
        if (courseService.getCourse(id) == null) return new ResponseEntity<>("No such course", HttpStatus.OK);
        sender.ask_to_sub(user_id, id);
        return new ResponseEntity<>("Sent subscription request", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam("user_id") int user_id, @RequestParam("code") int code){
        String result = "";
        if (sender.login(user_id,code)) {
            result = "Success";
            this.user_id = user_id;
        } else {
            result = "Wrong code";
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/subscriptions")
    public ResponseEntity<String> courseSubscriptions(){
        if (user_id == null) return new ResponseEntity<>("You're not signed in!", HttpStatus.OK);
        List<Integer> subscriptions = sender.get_subscriptions(user_id);
        List<Course> courses =  courseService.getCourses(subscriptions);
        StringBuilder stringBuilder = new StringBuilder();
        for (Course course : courses) {
            stringBuilder.append(course.briefDescription());
        }
        return new ResponseEntity<>(stringBuilder.toString(), HttpStatus.OK);
    }
}