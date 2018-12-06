package com.rpi.swd.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rpi.swd.entities.Task;
import com.rpi.swd.entities.User;
import com.rpi.swd.services.TaskService;
import com.rpi.swd.services.UserService;

@Controller
public class TaskController {

	@Autowired
	private TaskService taskService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/addTask")
	public String taskForm(String email, Model model, HttpSession session) {
		session.setAttribute("email", email);
		model.addAttribute("task", new Task());
		return "views/taskForm";
	}
	
	@PostMapping("/addTask")
	public String addTask(@Valid Task task, BindingResult bindingResult, HttpSession session) {
		if (bindingResult.hasErrors()) {
			return "views/taskForm";
		}
		String email = (String) session.getAttribute("email");
		taskService.addTask(task, userService.findOne(email));
		
		return "redirect:/users";
	}
	
	@GetMapping("/viewTasks")
	public String viewTasks(String email, Model model, HttpSession session) {
		
		session.setAttribute("email", email);
		User user = userService.findOne(email);
		
		model.addAttribute("tasks", taskService.findUserTask(user));
		
		return "views/profile";
	}
	
	@GetMapping("/updateTask")
	public String updateTask(String email, Long taskId, Model model, HttpSession session) {
		session.setAttribute("email", email);
		session.setAttribute("taskId", taskId);
		model.addAttribute("task", taskService.findTask(taskId));
		taskService.deleteTask(taskService.findTask(taskId));
		
		return "views/taskForm";
	}
	
	@RequestMapping("/deleteTask/{id}")
	public String deleteTask(@PathVariable Long id, String email) {
		taskService.deleteTask(taskService.findTask(id));
		return "redirect:/users";
		
	}
}
