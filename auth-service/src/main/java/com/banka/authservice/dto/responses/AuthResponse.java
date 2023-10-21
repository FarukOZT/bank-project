package com.banka.authservice.dto.responses;

import lombok.Data;

@Data
public class AuthResponse {

	String message;
	Long userId;
	String accessToken;
	String refreshToken;
	boolean isAuthenticated = false;
}
