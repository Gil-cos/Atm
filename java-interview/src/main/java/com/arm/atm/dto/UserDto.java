package com.arm.atm.dto;

import java.util.ArrayList;
import java.util.List;

import com.arm.atm.entity.Profile;
import com.arm.atm.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	private String userName;

	private List<Profile> profiles = new ArrayList<>();

	public UserDto(User newUser) {
		this.userName = newUser.getUsername();
		this.profiles = newUser.getProfiles();
	}


}
