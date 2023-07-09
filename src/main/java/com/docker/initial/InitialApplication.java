package com.docker.initial;

import com.docker.initial.modal.Role;
import com.docker.initial.modal.User;
import com.docker.initial.modal.UserRole;
import com.docker.initial.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@SpringBootApplication
public class InitialApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(InitialApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		var  user = User.builder()
//				.username("Abhi")
//				.password(bCryptPasswordEncoder.encode("abhi"))
//				.firstname("Abhijeet")
//				.lastname("Chakraborty")
//				.email("abhijeet.chakraborty@gmail.com")
//				.phone("9916607326")
//				.build();
//
//		var role = Role.builder()
//				.id(1L)
//				.role_name("ADMIN")
//				.build();
//
//		var role1 = Role.builder()
//				.id(2L)
//				.role_name("NORMAL")
//				.build();
//
//		Set<UserRole> userRoleSet = new HashSet<>();
//		var userRole = UserRole.builder()
//				.role(role)
//				.user(user)
//				.build();
//
//		var userRole1 = UserRole.builder()
//				.role(role1)
//				.user(user)
//				.build();
//
//		userRoleSet.add(userRole);
//		userRoleSet.add(userRole1);
//
//		this.userService.createUser(user, userRoleSet);
	}
}
