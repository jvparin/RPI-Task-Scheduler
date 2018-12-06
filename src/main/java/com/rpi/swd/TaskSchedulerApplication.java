package com.rpi.swd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rpi.swd.entities.User;
import com.rpi.swd.services.UserService;

@SpringBootApplication
public class TaskSchedulerApplication implements CommandLineRunner{
	@Autowired
	private UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(TaskSchedulerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		{
			User newAdmin = new User("admin@rpi.edu", "admin", "admin");
			userService.createAdmin(newAdmin);
			User newAdmin2 = new User("liuy24@rpi.edu", "Ingrid", "admin");
			userService.createAdmin(newAdmin2);
			User newAdmin3 = new User("chauha@rpi.edu", "Anuj", "admin2");
			userService.createAdmin(newAdmin3);
		}
		
	}
	
}
