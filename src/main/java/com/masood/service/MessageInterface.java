package com.masood.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.masood.model.Message;

public interface MessageInterface 
{
	public Message sendMessage(Message message);
	public void saveAll(List<Message> allmsg);
	public Optional<Message> getByid(Long id);
    public List<Message> getAllMessages();
    public List<Message> getBySenderId(Long id);
    public List<Message> getByRecieverId(Long id);
    public List<Message> getMessagesByDoctorUserId(Long doctorUserId);
    public List<Message> getMessagesByPatientUserId(Long patientUserId);
    public List<Message> getMessagesBySenderAndReceiver(Long senderId, Long receiverId);
    public List<Message> getMessagesBetweenDates(LocalDate startDate, LocalDate endDate);
    public List<Message> getMessagesByStatus(String status);
    public List<Message> getMessagesByDate(LocalDate date);
    public void deleteMessage(Long id);
    void deleteMessagesBySenderId(Long senderId);
    void deleteMessagesByReceiverId(Long receiverId);
    void deleteMessagesBySenderUsername(String senderUsername);
    void deleteMessagesByReceiverUsername(String receiverUsername);
    public List<Message> getMessageByUserandStatus(Long id, String status);
}
