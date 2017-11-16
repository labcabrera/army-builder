package com.app.security.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.app.security.domain.Role;
import com.app.security.domain.User;

public final class JwtUserFactory {

	private JwtUserFactory() {
	}

	public static JwtUser create(User user) {
		//@formatter:off
        return new JwtUser(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getRoles())
        );
        //formatter:on
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        role.stream().forEach(x -> authorities.add(new SimpleGrantedAuthority(x.name())));
        return authorities;
    }

}
