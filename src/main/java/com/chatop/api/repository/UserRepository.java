package com.chatop.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatop.api.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}