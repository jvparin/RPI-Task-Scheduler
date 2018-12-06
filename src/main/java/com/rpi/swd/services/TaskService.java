package com.rpi.swd.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rpi.swd.entities.Task;
import com.rpi.swd.entities.User;
import com.rpi.swd.repositories.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	public void addTask(Task task, User user) {
		task.setUser(user);
		taskRepository.save(task);
	}
	
	public List<Task> findUserTask(User user) {
		return taskRepository.findByUser(user);
	}
	
	public Task findTask(Long id) {
		return taskRepository.findById(id).get();
	}
	
	public void updateTask(Task task, User user) {
		task.setUser(user);
		taskRepository.save(task);
	}
	
	public void deleteTask(Task task) {
		taskRepository.delete(task);
	}
}
