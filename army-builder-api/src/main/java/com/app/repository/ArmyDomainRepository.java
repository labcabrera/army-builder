package com.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.app.domain.ArmyDomain;

@Repository
public interface ArmyDomainRepository extends MongoRepository<ArmyDomain, String> {

	ArmyDomain findByName(String name);

}
