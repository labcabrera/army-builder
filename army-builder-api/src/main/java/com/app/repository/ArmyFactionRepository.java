package com.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.app.domain.ArmyFaction;

@Repository
public interface ArmyFactionRepository extends MongoRepository<ArmyFaction, String> {

}
