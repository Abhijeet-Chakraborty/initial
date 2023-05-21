package com.docker.initial.repository;

import com.docker.initial.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);



    public void deleteById(Long userId);

}
