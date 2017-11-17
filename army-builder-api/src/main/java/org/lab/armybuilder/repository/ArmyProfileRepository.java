package org.lab.armybuilder.repository;

import org.lab.armybuilder.domain.ArmyProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArmyProfileRepository extends MongoRepository<ArmyProfile, String> {

}
