package com.arm.atm.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.arm.atm.entity.Profile;
import com.arm.atm.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	private Long id;
	
	private String userName;

	private List<String> profiles = new ArrayList<>();

	public UserDto(User newUser) {
		this.id = newUser.getId();
		this.userName = newUser.getUsername();
		this.profiles = newUser.getProfiles()
					.stream()
					.map(Profile::getName)
					.collect(Collectors.toList());
	}

	public static Page<UserDto> convert(Page<User> users) {
		return users.map(UserDto::new);
	}

}
