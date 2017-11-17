package org.lab.armybuilder.controller;

import java.util.List;

import org.lab.armybuilder.domain.ArmyDomain;
import org.lab.armybuilder.repository.ArmyDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/api/domains")
public class ArmyDomainController {

	@Autowired
	private ArmyDomainRepository repo;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<ArmyDomain>> get() {
		return ResponseEntity.ok(repo.findAll());
	}

}
