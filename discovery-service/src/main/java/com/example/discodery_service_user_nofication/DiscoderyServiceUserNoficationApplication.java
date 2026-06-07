package com.example.discodery_service_user_nofication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class DiscoderyServiceUserNoficationApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscoderyServiceUserNoficationApplication.class, args);
	}

}
