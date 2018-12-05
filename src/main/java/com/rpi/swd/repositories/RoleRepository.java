package com.rpi.swd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rpi.swd.entities.Role;

public interface RoleRepository extends JpaRepository<Role, String>{

}
