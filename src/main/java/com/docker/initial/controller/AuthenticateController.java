package com.docker.initial.controller;

import com.docker.initial.configuration.JwtUtils;
import com.docker.initial.modal.JwtRequest;
import com.docker.initial.modal.JwtResponse;
import com.docker.initial.modal.User;
import com.docker.initial.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticateController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) {

        authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());

        var userDetails = userDetailService.loadUserByUsername(jwtRequest.getUsername());
        var token = jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    //Returns the details of current user
    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal){
        return (User) this.userDetailService.loadUserByUsername(principal.getName());
    }
}
