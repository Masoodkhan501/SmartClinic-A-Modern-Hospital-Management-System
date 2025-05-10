package com.masood.model;

import java.util.Date;

public class User {
	private Long id;
	private String name;
	private String email;
	private String password;
	private Role role;
	private Date createdAt;

	public User() {
	}

	public User(String name, String email, String password, Role role, Date createdAt) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role
				+ ", createdAt=" + createdAt + "]";
	}

}
