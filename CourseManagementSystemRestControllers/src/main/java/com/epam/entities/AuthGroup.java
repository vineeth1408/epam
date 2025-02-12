package com.epam.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AUTH_USER_GROUP")
public class AuthGroup {
	
	@Id
	@Column(name = "AUTH_USER_GROUP_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "USERNAME")
	private String username;
	
	@Column(name = "AUTH_GROUP")
	private String authenticGroup;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthenticGroup() {
		return authenticGroup;
	}

	public void setAuthenticGroup(String authenticGroup) {
		this.authenticGroup = authenticGroup;
	}
}
