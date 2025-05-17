package com.masood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masood.model.MessageHistory;

public interface MessageHistoryRepo extends JpaRepository<MessageHistory, Long> {

}
