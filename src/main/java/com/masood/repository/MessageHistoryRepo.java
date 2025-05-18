package com.masood.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masood.model.Message;
import com.masood.model.MessageHistory;

public interface MessageHistoryRepo extends JpaRepository<MessageHistory, Long>
{
	 public List<MessageHistory> findByMessage(Message message);

	    // Get by date
	 public List<MessageHistory> findByUpdatedAtBetween(LocalDateTime start, LocalDateTime end);

	    // Get by reason
	 public List<MessageHistory> findByUpdateReasonContainingIgnoreCase(String reason);
	 
	 public List<MessageHistory> findBySender_IdAndReceiver_Id(Long senderId, Long receiverId);

	 public List<MessageHistory> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
