package com.masood.service;

import java.time.LocalDate;
import java.util.List;

import com.masood.model.MessageHistory;

public interface MessageHistoryInterface
{
	 public MessageHistory archiveMessage(MessageHistory messageHistory);
	 public List<MessageHistory> getAllHistory();
	 public List<MessageHistory> getHistoryByUserId(Long UserId);
	 public List<MessageHistory> getHistoryByDate(LocalDate date);
	 public List<MessageHistory> getHistoryBySenderAndReceiver(Long senderId, Long receiverId);
	 public List<MessageHistory> getHistoryBetweenDates(LocalDate startDate, LocalDate endDate);
	 public void deletebyId(Long msghis);
	 public void deletebyCustomDate(LocalDate date);
	 public List<MessageHistory> getByMessageSubject(String subject);
}
