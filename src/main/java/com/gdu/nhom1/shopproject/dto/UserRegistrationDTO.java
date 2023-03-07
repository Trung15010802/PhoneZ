package com.gdu.nhom1.shopproject.dto;

import lombok.Data;

@Data
public class UserRegistrationDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public UserRegistrationDTO() {
	}

	public UserRegistrationDTO(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
