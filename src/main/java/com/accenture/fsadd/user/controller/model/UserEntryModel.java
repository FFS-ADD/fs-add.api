package com.accenture.fsadd.user.controller.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserEntryModel implements Serializable{
	
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 2856888979821810011L;
	/**
	 * ID
	 */
    @JsonProperty("id")
	private String id;

	/**
	 * Email Address
	 */
    @JsonProperty("email")
	private String email;

	/**
	 * Password
	 */
    @JsonProperty("password")
	private String password;

	/**
	 * Roles
	 */
    @JsonProperty("role")
	private String role;

	/**
	 * avatar
	 */
    @JsonProperty("avatar")
	private String avatar;

	/**
	 * fristName
	 */
    @JsonProperty("firstName")
	private String firstName;

	/**
	 * lastName
	 */
    @JsonProperty("lastName")
	private String lastName;

	/**
	 * project
	 */
    @JsonProperty("project")
	private String project;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}
	
	
}
