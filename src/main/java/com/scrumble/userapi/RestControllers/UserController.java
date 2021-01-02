package com.scrumble.userapi.RestControllers;

import com.scrumble.userapi.Logic.UserService;
import com.scrumble.userapi.Models.User;
import com.scrumble.userapi.Resources.CreateUserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        User newUser = new User.Builder(user.getName())
                .build();

        return new ResponseEntity<>(userService.create(newUser), HttpStatus.OK);
    }
}
