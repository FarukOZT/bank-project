package com.banka.authservice.dto.responses;

import com.banka.authservice.entity.User;
import lombok.Data;

@Data
public class UserResponse {
	
	Long id;
	int avatarId;
	String name;

	public UserResponse(User entity) {
		this.id = entity.getId();
		this.name = entity.getName();
	} 
}
