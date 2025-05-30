package com.masood.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.masood.model.MessageHistory;
import com.masood.repository.MessageHistoryRepo;

public class MessageHistroyImpl implements MessageHistoryInterface {
	
	@Autowired
	private MessageHistoryRepo mhr;

	public MessageHistory archiveMessage(MessageHistory messageHistory) {
		return mhr.save(messageHistory);
	}

	public List<MessageHistory> getAllHistory() {
		return mhr.findAll();
	}
	
	public List<MessageHistory> getHistoryByUserId(Long UserId) {
		return mhr.findByMessageUserId(UserId);
	}

	public List<MessageHistory> getHistoryByDate(LocalDate date) {
		return mhr.findByUpdatedAt(date);
	}

	public List<MessageHistory> getHistoryBySenderAndReceiver(Long senderId, Long receiverId) {
		return mhr.findBySenderIdAndReceiverId(senderId, receiverId);
	}

	public List<MessageHistory> getHistoryBetweenDates(LocalDate startDate, LocalDate endDate) {
		return mhr.findByUpdatedAtBetween(startDate, endDate);
	}

	public void deletebyId(Long msghis) {
		mhr.deleteById(msghis);
	}

	public void deletebyCustomDate(LocalDate date) {
		mhr.deleteMessagesOlderThan(date);
	}

	public List<MessageHistory> getByMessageSubject(String subject) {
		return mhr.findByMessageSubject(subject);
	}

}
