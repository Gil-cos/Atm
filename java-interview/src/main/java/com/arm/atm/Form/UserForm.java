package com.arm.atm.Form;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.arm.atm.entity.Profile;
import com.arm.atm.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {
	
	@NotNull @NotEmpty
	private String userName;

	@NotNull @NotEmpty
	private String password;

	public User convert(PasswordEncoder encoder) {
		
		List<Profile> profiles = new ArrayList<Profile>();
		profiles.add(new Profile(2L, "ROLE_COSTUMER"));
		
		User user = new User(null, this.userName, encoder.encode(this.password), profiles);
		
		return user;
	}

}
