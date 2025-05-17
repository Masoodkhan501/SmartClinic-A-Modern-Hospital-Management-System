package com.masood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masood.model.Message;

public interface MessageRepo extends JpaRepository<Message, Long> {

}
