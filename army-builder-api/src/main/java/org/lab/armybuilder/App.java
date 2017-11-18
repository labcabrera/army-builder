package org.lab.armybuilder;

import org.lab.armybuilder.bootstrap.AppInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Autowired
	private AppInitializer appInitializer;

	@Bean
	CommandLineRunner preLoadMongo() throws Exception {
		return args -> {
			if (!appInitializer.check()) {
				appInitializer.run();
			}
		};
	}
}
