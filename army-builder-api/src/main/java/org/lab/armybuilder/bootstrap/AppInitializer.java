package org.lab.armybuilder.bootstrap;

import java.util.Arrays;

import org.lab.armybuilder.domain.ArmyDomain;
import org.lab.armybuilder.domain.ArmyFaction;
import org.lab.armybuilder.domain.ArmyProfile;
import org.lab.armybuilder.repository.ArmyDomainRepository;
import org.lab.armybuilder.repository.ArmyFactionRepository;
import org.lab.armybuilder.repository.ArmyProfileRepository;
import org.lab.armybuilder.repository.UserRepository;
import org.lab.armybuilder.security.domain.Role;
import org.lab.armybuilder.security.domain.User;
import org.lab.armybuilder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AppInitializer implements Runnable {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;
	@Autowired
	private ArmyDomainRepository armyDomainRepository;
	@Autowired
	private ArmyFactionRepository armyFactionRepository;
	@Autowired
	private ArmyProfileRepository armyProfileRepository;

	public boolean check() {
		if (userRepository.count() < 1) {
			log.info("Database is not initialized");
			return false;
		}
		return true;
	}

	@Override
	public void run() {
		log.warn("======================================================");
		log.warn("= Detected empty mongodb schema                      =");
		log.warn("= Loading data initialization script                 =");
		log.warn("======================================================");

		//@formatter:off
		User admin =User.builder()
			.name("admin")
			.password("admin")
			.email("admin@armybuilder.com")
			.roles(Arrays.asList(Role.ADMIN))
			.build();
		User user = User.builder()
			.name("user")
			.password("user")
			.email("user@armybuilder.com")
			.roles(Arrays.asList(Role.USER))
			.build();
		//@formatter:on

		userService.save(admin);
		userService.save(user);

		armyProfileRepository.deleteAll();
		armyFactionRepository.deleteAll();
		armyDomainRepository.deleteAll();

		ArmyDomain testDomain01 = ArmyDomain.builder().name("domain-01").publicDomain(true).owner(admin).version("0.1")
			.build();
		ArmyDomain testDomain02 = ArmyDomain.builder().name("domain-02").publicDomain(true).owner(admin).version("0.1")
			.build();
		armyDomainRepository.insert(testDomain01);
		armyDomainRepository.insert(testDomain02);

		ArmyFaction testFaction01 = ArmyFaction.builder().name("faction-test-01").domain(testDomain01).build();
		ArmyFaction testFaction02 = ArmyFaction.builder().name("faction-test-02").domain(testDomain01).build();

		armyFactionRepository.save(testFaction01);
		armyFactionRepository.save(testFaction02);

		ArmyProfile profile01 = ArmyProfile.builder().name("profile-01").faction(testFaction01).build();
		ArmyProfile profile02 = ArmyProfile.builder().name("profile-02").faction(testFaction01).build();

		armyProfileRepository.saveAll(Arrays.asList(profile01, profile02));

		log.warn("Data initialized");
	}

}
