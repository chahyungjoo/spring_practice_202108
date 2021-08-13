package com.practice.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {
	
	SUPERADMIN("ROLE_ADMIN"),
	ADMIN("ROLE_ADMIN"),
	MEMBER("ROLE_MEMBER");
	
	private final String role;
	Role(String role) {
		this.role = role;
	}
	
	@JsonValue
	@Override
	public String toString() {
		return role;
	}
}
