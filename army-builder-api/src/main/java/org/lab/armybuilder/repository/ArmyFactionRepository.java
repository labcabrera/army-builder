package org.lab.armybuilder.repository;

import org.lab.armybuilder.domain.ArmyFaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArmyFactionRepository extends MongoRepository<ArmyFaction, String> {

}
