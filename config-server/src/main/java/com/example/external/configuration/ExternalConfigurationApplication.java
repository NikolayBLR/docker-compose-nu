package com.example.external.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ExternalConfigurationApplication {

	public static void main(String[] args) {

		SpringApplication.run(ExternalConfigurationApplication.class, args);
	}

}
