package com.masood.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "message_history")
public class MessageHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Reference to the original message
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "message_id", nullable = false)
	private Message message;

	// Snapshot of message content at a certain time (to track edits or resends)
	@Column(length = 2000)
	private String contentSnapshot;

	private LocalDate updatedAt;

	private String updateReason; // e.g., "Edited by user", "Resent due to failure"

	public MessageHistory() {
	}

	public MessageHistory(Message message, String contentSnapshot, LocalDate updatedAt, String updateReason) {
		this.message = message;
		this.contentSnapshot = contentSnapshot;
		this.updatedAt = updatedAt;
		this.updateReason = updateReason;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public String getContentSnapshot() {
		return contentSnapshot;
	}

	public void setContentSnapshot(String contentSnapshot) {
		this.contentSnapshot = contentSnapshot;
	}

	public LocalDate getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUpdateReason() {
		return updateReason;
	}

	public void setUpdateReason(String updateReason) {
		this.updateReason = updateReason;
	}

	@Override
	public String toString() {
		return "MessageHistory [" + (id != null ? "id=" + id + ", " : "")
				+ (message != null ? "message=" + message + ", " : "")
				+ (contentSnapshot != null ? "contentSnapshot=" + contentSnapshot + ", " : "")
				+ (updatedAt != null ? "updatedAt=" + updatedAt + ", " : "")
				+ (updateReason != null ? "updateReason=" + updateReason : "") + "]";
	}

	}
