package com.accenture.fsadd.user.entity;

public enum Role {

	USERS("USERS"),
	ADMIN("ADMINS");
	
	private String roleName;
	
	public String getRoleName() {
		return roleName;
	}

	private Role(String roleName){
		this.roleName = roleName;
		
	}

}
