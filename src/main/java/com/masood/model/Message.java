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
@Table(name = "messages")
public class Message 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;

    @Column(length = 2000)
    private String content;

    private LocalDate sentAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_user_id", nullable = false)
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_user_id", nullable = false)
    private User receiver;

    // Status can be useful (e.g., SENT, READ)
    private String status;

    public Message() {
    }

    public Message(String subject, String content, LocalDate sentAt, User sender, User receiver, String status) {
        this.subject = subject;
        this.content = content;
        this.sentAt = sentAt;
        this.sender = sender;
        this.receiver = receiver;
        this.status = status;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDate getSentAt() {
		return sentAt;
	}

	public void setSentAt(LocalDate sentAt) {
		this.sentAt = sentAt;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Message [" + (id != null ? "id=" + id + ", " : "")
				+ (subject != null ? "subject=" + subject + ", " : "")
				+ (content != null ? "content=" + content + ", " : "")
				+ (sentAt != null ? "sentAt=" + sentAt + ", " : "") + (sender != null ? "sender=" + sender + ", " : "")
				+ (receiver != null ? "receiver=" + receiver + ", " : "") + (status != null ? "status=" + status : "")
				+ "]";
	}
    
    
}
