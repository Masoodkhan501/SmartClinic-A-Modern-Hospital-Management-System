package com.masood.service;

import java.time.LocalDate;
import java.util.List;

import com.masood.model.MessageHistory;

public interface MessageHistoryInterface
{
	 public MessageHistory archiveMessage(MessageHistory messageHistory);
	 public List<MessageHistory> getAllHistory();
	 public List<MessageHistory> getHistoryByDoctorUserId(Long doctorUserId);
	 public List<MessageHistory> getHistoryByPatientUserId(Long patientUserId);
	 public List<MessageHistory> getHistoryByStatus(String status);
	 public List<MessageHistory> getHistoryByDate(LocalDate date);
	 public List<MessageHistory> getHistoryBySenderAndReceiver(Long senderId, Long receiverId);
	 public List<MessageHistory> getHistoryBetweenDates(LocalDate startDate, LocalDate endDate);
}
