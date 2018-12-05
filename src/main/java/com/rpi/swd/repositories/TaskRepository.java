package com.rpi.swd.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rpi.swd.entities.Task;
import com.rpi.swd.entities.User;

public interface TaskRepository extends JpaRepository<Task, Long>{

	List<Task> findByUser(User user);

}
