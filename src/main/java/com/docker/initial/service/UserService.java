package com.docker.initial.service;

import com.docker.initial.modal.User;
import com.docker.initial.modal.UserRole;

import java.util.Set;


public interface UserService {

    public User createUser(User user, Set<UserRole> userRoles);
    public User getUser(String username);
    public void deleteUser(Long userId);
}
