package com.scrumble.userapi.RestControllers;

import com.scrumble.userapi.Logic.UserService;
import com.scrumble.userapi.Models.User;
import com.scrumble.userapi.Resources.CreateUserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"*"})
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public Iterable<User> all() {
        return userService.getAll();
    }

    @GetMapping("/users/{id}")
    ResponseEntity<User> getById(@PathVariable("id") int id) {
        User user = userService.getById(id);
        return user != null ? new ResponseEntity<>(user, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/users")
    ResponseEntity<User> newUser(@RequestBody final CreateUserResource user) {
        User newUser = new User.Builder(user.getName(), user.getPassword())
                .build();

        return new ResponseEntity<>(userService.create(newUser), HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    ResponseEntity<User> updateUser(@PathVariable("id") int id ,@RequestBody final CreateUserResource user) {
        User existing = userService.getById(id);

        if (existing == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        existing.setPoints(existing.getPoints() + user.getPoints());

        return new ResponseEntity<>(userService.update(existing), HttpStatus.OK);
    }

    @PostMapping("/users/login")
    ResponseEntity<User> checkUser(@RequestBody final CreateUserResource user) {
        List<User> users = userService.getAll();
        User existing = users.stream().filter(o -> o.getName().equals(user.getName())).findFirst().get();
        if(existing.getPassword().equals(user.getPassword())) {
            return new ResponseEntity<>(existing, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
