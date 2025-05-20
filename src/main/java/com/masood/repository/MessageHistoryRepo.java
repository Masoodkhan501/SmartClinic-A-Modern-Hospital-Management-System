package com.masood.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.masood.model.Message;
import com.masood.model.MessageHistory;

public interface MessageHistoryRepo extends JpaRepository<MessageHistory, Long>
{
	 public List<MessageHistory> findByMessage(Message message);

	    // Get by date
	 public List<MessageHistory> findByUpdatedAtBetween(LocalDateTime start, LocalDateTime end);

	    // Get by reason
	 public List<MessageHistory> findByUpdateReasonContainingIgnoreCase(String reason);
	 
	 @Query("SELECT mh FROM MessageHistory mh WHERE mh.message.sender.id = :senderId AND mh.message.receiver.id = :receiverId")
	 public List<MessageHistory> findBySenderIdAndReceiverId(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId);

//	 public List<MessageHistory> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
