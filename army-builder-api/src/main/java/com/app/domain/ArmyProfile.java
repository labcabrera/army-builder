package com.app.domain;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "zprofiles")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArmyProfile {

	@Id
	String id;

	@NotNull
	String name;

	@DBRef
	@NotNull
	ArmyFaction faction;

}
