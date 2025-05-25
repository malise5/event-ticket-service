package com.malise5.tickets.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malise5.tickets.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {

    
}
