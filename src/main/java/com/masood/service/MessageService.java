package com.masood.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masood.model.Message;
import com.masood.repository.MessageRepo;

import jakarta.transaction.Transactional;
@Service("MessageSerivce")
@Transactional
public class MessageService implements MessageInterface
{
	@Autowired
	private MessageRepo mr;

	public Message sendMessage(Message message) {
		return mr.save(message);
	}

	public List<Message> getAllMessages() {
		return mr.findAll();
	}

	public List<Message> getMessagesByDoctorUserId(Long doctorUserId) {
		return mr.findByDoctorSenderId(doctorUserId);
	}

	public List<Message> getMessagesByPatientUserId(Long patientUserId) {
		return mr.findByPatientReceiverId(patientUserId);
	}

	public List<Message> getMessagesBySenderAndReceiver(Long senderId, Long receiverId) {
		return mr.findBySenderAndReceiver(senderId, receiverId);
	}

	public List<Message> getMessagesBetweenDates(LocalDate startDate, LocalDate endDate) {
		return mr.findBySentAtBetween(startDate, endDate);
	}

	public List<Message> getMessagesByStatus(String status) {
		return mr.findByStatus(status);
	}

	public List<Message> getMessagesByDate(LocalDate date) {
		return mr.findByDate(date);
	}

	public void deleteMessage(Long id) {
		mr.deleteById(id);
	}

	public Optional<Message> getByid(Long id) {
		return mr.findById(id);
	}

	public void deleteMessagesBySenderId(Long senderId) {
		mr.deleteBySender_Id(senderId);
	}

	public void deleteMessagesByReceiverId(Long receiverId) {
		mr.deleteByReceiver_Id(receiverId);
	}

	public void deleteMessagesBySenderUsername(String senderUsername) {
		mr.deleteBySenderUsername(senderUsername);
	}

	public void deleteMessagesByReceiverUsername(String receiverUsername) {
		mr.deleteByReceiverUsername(receiverUsername);
	}

}
