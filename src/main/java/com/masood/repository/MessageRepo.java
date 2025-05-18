package com.masood.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.masood.model.Message;

public interface MessageRepo extends JpaRepository<Message, Long> 
{
	public List<Message> findBySender_Id(Long senderId);
    public List<Message> findByReceiver_Id(Long receiverId);

    // Find by sender or receiver name
    @Query("SELECT m FROM Message m WHERE m.sender.name = :name")
    public List<Message> findBySenderName(String name);

    @Query("SELECT m FROM Message m WHERE m.receiver.name = :name")
    public List<Message> findByReceiverName(String name);

    // Find all messages between two users
    public List<Message> findBySenderAndReceiver(Long sender, Long receiver);

    // Find messages by status (e.g., SENT, READ)
    public List<Message> findByStatus(String status);

    // Find messages sent on a specific date or date range
    public List<Message> findBySentAtBetween(LocalDate start, LocalDate end);
    
    @Query("SELECT m FROM Message m WHERE m.sender.role = 'DOCTOR' AND m.sender.id = :doctorUserId")
    public List<Message> findByDoctorSenderId(Long doctorUserId);

    @Query("SELECT m FROM Message m WHERE m.receiver.role = 'PATIENT' AND m.receiver.id = :patientUserId")
    public List<Message> findByPatientReceiverId(Long patientUserId);
    
    public List<Message> findByDate(LocalDate date);
    
    void deleteBySender_Id(Long senderId);
    void deleteByReceiver_Id(Long receiverId);	
    
    @Query("DELETE FROM Message m WHERE m.receiver.username = :receiverUsername")
    void deleteByReceiverUsername(@Param("receiverUsername") String receiverUsername);
    
    @Query("DELETE FROM Message m WHERE m.receiver.username = :senderUsername")
    void deleteBySenderUsername(@Param("senderUsername") String senderUsername);
}
