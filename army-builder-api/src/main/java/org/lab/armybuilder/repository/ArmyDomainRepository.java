package org.lab.armybuilder.repository;

import org.lab.armybuilder.domain.ArmyDomain;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArmyDomainRepository extends MongoRepository<ArmyDomain, String> {

	ArmyDomain findByName(String name);

}
