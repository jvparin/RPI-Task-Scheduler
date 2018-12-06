package com.rpi.swd.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rpi.swd.entities.User;

public interface UserRepository extends JpaRepository<User, String>{

	List<User> findByNameLike(String name);

}
