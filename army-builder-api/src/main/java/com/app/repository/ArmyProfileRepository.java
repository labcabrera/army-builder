package com.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.app.domain.ArmyProfile;

@Repository
public interface ArmyProfileRepository extends MongoRepository<ArmyProfile, String> {

}
