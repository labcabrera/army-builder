package com.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.app.bootstrap.AppInitializer;

@SpringBootApplication
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
