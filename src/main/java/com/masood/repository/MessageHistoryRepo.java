package com.masood.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.masood.model.MessageHistory;

public interface MessageHistoryRepo extends JpaRepository<MessageHistory, Long>
{
	
	
	@Query("select mh from MessageHistory where mh.message.subject = :message")
	public List<MessageHistory> findByMessageSubject(@Param("message") String message);

	public List<MessageHistory> findByUpdatedAt(LocalDate date);

	    // Get by date
	public List<MessageHistory> findByUpdatedAtBetween(LocalDateTime start, LocalDateTime end);

	    // Get by reason
	public List<MessageHistory> findByUpdateReasonContainingIgnoreCase(String reason);
	 
	@Query("SELECT mh FROM MessageHistory mh WHERE mh.message.sender.id = :senderId AND mh.message.receiver.id = :receiverId")
	public List<MessageHistory> findBySenderIdAndReceiverId(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId);

	public List<MessageHistory> findByDateBetween(LocalDate startDate, LocalDate endDate);
	 
	@Query("select mh from MessageHistory mh where mh.message.sender.id = :id or mh.message.receiver.id = :id")
	public List<MessageHistory> findByMessageUserId(@Param("id") Long id);
	 
	@Query("DELETE FROM Message m WHERE m.sentAt < :cutoffDate")
	public void deleteMessagesOlderThan(@Param("cutoffDate") LocalDate cutoffDate);
}
