package org.lab.armybuilder.security.auth;

import java.util.ArrayList;
import java.util.List;

import org.lab.armybuilder.security.domain.Role;
import org.lab.armybuilder.security.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

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
