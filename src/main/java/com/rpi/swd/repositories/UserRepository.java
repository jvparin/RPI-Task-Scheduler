package com.rpi.swd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rpi.swd.entities.User;

public interface UserRepository extends JpaRepository<User, String>{

}
