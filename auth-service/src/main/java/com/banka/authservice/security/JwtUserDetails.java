package com.banka.authservice.security;

import com.banka.authservice.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class JwtUserDetails implements UserDetails {

	public Long id;
	private String name;
	private String lastName;
	private String password;
	private String email;

	private Collection<? extends GrantedAuthority> authorities;

	public JwtUserDetails(Long id, String name, String lastName, String email, String password, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.authorities = authorities;
	}

	public static JwtUserDetails create(User user) {
        List<GrantedAuthority> authoritiesList = new ArrayList<>();
		authoritiesList.add(new SimpleGrantedAuthority("User"));
        return new JwtUserDetails(user.getId(), user.getName(), user.getLastName(), user.getEmail(), user.getPassword(), authoritiesList);
    }

	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
