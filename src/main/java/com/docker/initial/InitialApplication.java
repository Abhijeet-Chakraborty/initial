package com.docker.initial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InitialApplication {
	public static Logger LOGGER = LoggerFactory.getLogger(InitialApplication.class);

	public static void main(String[] args) {
		LOGGER.info("Application Started.......");
		SpringApplication.run(InitialApplication.class, args);
	}
}
