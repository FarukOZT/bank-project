package com.banka.authservice.dto.requests;

import lombok.Data;

@Data
public class UserRequest {

	Long id;
	String name;
	String lastName;
	String password;
	String email;

}
