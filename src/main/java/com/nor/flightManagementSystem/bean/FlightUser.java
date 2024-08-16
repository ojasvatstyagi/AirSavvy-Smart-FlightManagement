package com.nor.flightManagementSystem.bean;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "flight_users")
public class FlightUser implements UserDetails {
	@Id
	private String username;
	@Size(min = 8)
	@NotBlank
	@Field("password")
	private String password;
	@Email
	private String email;
	private boolean isEnabled = false;
	private String role;
	@Version
	private Long version;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// Return roles or authorities. Placeholder example:
		return List.of(new SimpleGrantedAuthority(role));
	}

	@Override
	public boolean isAccountNonExpired() {
		return true; // Change to 'true' to allow login
	}

	@Override
	public boolean isAccountNonLocked() {
		return true; // Change to 'true' to allow login
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true; // Change to 'true' to allow login
	}

	@Override
	public boolean isEnabled() {
		return isEnabled; // Use the isEnabled field
	}
}