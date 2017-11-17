package org.lab.armybuilder.security.domain;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "zusers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SuppressWarnings("serial")
public class User implements Serializable {

	@Id
	private String id;

	@NotNull
	private String name;

	@Email
	private String email;

	@NotNull
	@JsonIgnore
	private String password;

	private List<Role> roles;

}
