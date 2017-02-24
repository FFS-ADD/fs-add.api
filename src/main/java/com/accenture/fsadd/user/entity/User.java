package com.accenture.fsadd.user.entity;

import java.math.BigInteger;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;

public class User {

	/**
	 * ID
	 */
    @Id
	private String id;

	/**
	 * Email Address
	 */
	private String email;

	/**
	 * Password
	 */
	private String password;

	/**
	 * Roles
	 */
	private String role;

	/**
	 * avatar
	 */
	private String avatar;

	/**
	 * fristName
	 */
	private String firstName;

	/**
	 * lastName
	 */
	private String lastName;

	/**
	 * project
	 */
	private String project;

	public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

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

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
