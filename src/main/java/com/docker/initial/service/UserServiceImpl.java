package com.docker.initial.service;

import com.docker.initial.exception.UserFoundException;
import com.docker.initial.modal.User;
import com.docker.initial.modal.UserRole;
import com.docker.initial.repository.RoleRepository;
import com.docker.initial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public User createUser(User user, Set<UserRole> userRoles) {
        var existingUser = userRepository.findByUsername(user.getUsername());
        if(Objects.nonNull(existingUser)) {
            throw new UserFoundException();
        } else {
            userRoles.forEach(userRole -> {
                roleRepository.save(userRole.getRole());
            });
            user.getUserRole().addAll(userRoles);
            existingUser = userRepository.save(user);
        }
        return existingUser;
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }


}
