package org.lab.armybuilder.security.auth;

import java.io.Serializable;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class  JwtAuthenticationRequest implements Serializable {

    private String username;
    private String email;
    private String password;

    public JwtAuthenticationRequest() {
        super();
    }

    public JwtAuthenticationRequest(String username, String email, String password) {
        this.setUsername(username);
        this.setEmail(email);
        this.setPassword(password);
    }
}
