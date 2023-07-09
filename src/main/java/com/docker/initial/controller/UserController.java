package com.docker.initial.controller;

import com.docker.initial.exception.UserFoundException;
import com.docker.initial.modal.Role;
import com.docker.initial.modal.User;
import com.docker.initial.modal.UserRole;
import com.docker.initial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping(value = "/")
    @ResponseBody
    public ResponseEntity<Object> createUser(@RequestBody User user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        var role = Role.builder()
                .role_name("NORMAL")
                .build();

        var userRole = UserRole.builder()
                .user(user)
                .role(role)
                .build();

        var createdUser = userService.createUser(user, Set.of(userRole));

        return new ResponseEntity<Object>(Objects.nonNull(createdUser) ? createdUser : "User already present!!", HttpStatus.OK);
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

    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<?> exceptionHandler(UserFoundException exception) {
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
