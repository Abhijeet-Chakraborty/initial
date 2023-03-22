package com.docker.initial;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class InitialApplicationTests {

	public static Logger LOGGER = LoggerFactory.getLogger(InitialApplication.class);
	@Test
	void contextLoads() {
		LOGGER.info("Test Started.......");
		assertEquals(true, true);
	}

}
