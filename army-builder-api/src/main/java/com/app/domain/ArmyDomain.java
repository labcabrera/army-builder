package com.app.domain;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.app.security.domain.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "zdomains")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArmyDomain {

	@Id
	String id;

	@NotNull
	String name;

	@NotNull
	@DBRef
	User owner;

	@NotNull
	Boolean publicDomain;

	@NotNull
	String version;

}
