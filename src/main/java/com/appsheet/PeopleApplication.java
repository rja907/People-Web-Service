package com.appsheet;

import com.appsheet.people.controller.UserController;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@Slf4j
@SpringBootApplication
public class PeopleApplication {
	static UserController userController = new UserController();

	public static void main(String[] args) {

		SpringApplication.run(PeopleApplication.class, args);

		// If you choose to run the java -jar command, this will return the required users on the command line.
		System.out.println(userController.getRequiredUsers());
	}

}
