package com.example.JavaLabSpringCloudAccount;

import com.example.JavaLabSpringCloudAccount.model.Account;
import com.example.JavaLabSpringCloudAccount.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccController {
    private String name = "user";

    @Autowired
    public AccountService accountService;


    @GetMapping("/user/{id}")
    public ResponseEntity<String> setName(@PathVariable int id) {
        Account user = accountService.getAccount(id);
        if (user == null) return new ResponseEntity<String>("No such user", HttpStatus.OK);
        return new ResponseEntity<String>(user.toString(), HttpStatus.OK);
    }

    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribe(@RequestParam("userId") int userId, @RequestParam("courseId") int courseId ) {
        accountService.addSubscription(userId, courseId);
        return new ResponseEntity<>("UID: "+userId+" COURSEID: "+courseId, HttpStatus.OK);
    }

    @PostMapping("/unsubscribe")
    public ResponseEntity<String> unsubscribe(@RequestParam("userId") int userId, @RequestParam("courseId") int courseId ) {
        accountService.removeSubscription(userId, courseId);
        return new ResponseEntity<>("UID: "+userId+" COURSEID: "+courseId, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam("username") String username) {
        Account user = new Account();
        user.setName(username);
        accountService.addAccount(user);
        return new ResponseEntity<>("New user\n"+user.toString(), HttpStatus.OK);
    }
}
