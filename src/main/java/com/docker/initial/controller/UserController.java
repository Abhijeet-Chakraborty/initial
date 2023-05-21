package com.docker.initial.controller;

import com.docker.initial.modal.Role;
import com.docker.initial.modal.User;
import com.docker.initial.modal.UserRole;
import com.docker.initial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/")
    @ResponseBody
    public ResponseEntity<User> createUser(@RequestBody User user) {

        var role = Role.builder()
                .role_name("NORMAL")
                .build();

        var userRole = UserRole.builder()
                .user(user)
                .role(role)
                .build();
        return new ResponseEntity<User>(userService.createUser(user, Set.of(userRole)), HttpStatus.OK);
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<User> getUser(@PathVariable(value = "username") String username) {
        return new ResponseEntity<>(userService.getUser(username), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "userId") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<String>("User Deleted!!", HttpStatus.OK);
    }

}
