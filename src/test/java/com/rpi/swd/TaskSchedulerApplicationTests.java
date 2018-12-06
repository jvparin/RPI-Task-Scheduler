package com.rpi.swd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rpi.swd.entities.Task;
import com.rpi.swd.entities.User;
import com.rpi.swd.services.TaskService;
import com.rpi.swd.services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskSchedulerApplicationTests {

	@Autowired
	private UserService userService;
	@Autowired
	private TaskService taskService;
	
	@Before
	public void initDb() {
		{
			User newUser = new User("testUser@email.com", "testUser", "testPassword");
			userService.createUser(newUser);
		}
		{
			User newUser = new User("testAdmin@email.com", "testAdmin", "testPassword");
			userService.createUser(newUser);
		}
		
		Task userTask = new Task("12/07/2018", "02:00", "03:00", "You need to work today");
		User user = userService.findOne("testUser@email.com");
		taskService.addTask(userTask, user);
	}
	
	@Test
	public void testUser() {
		User user = userService.findOne("testUser@email.com");
		assertNotNull(user);
		User admin = userService.findOne("testAdmin@email.com");
		assertEquals(admin.getEmail(), "testAdmin@email.com");
	}
	
	@Test
	public void testTask() {
		User user = userService.findOne("testUser@email.com");
		List<Task> task = taskService.findUserTask(user);
		assertNotNull(task);
	}

}
