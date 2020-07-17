package org.onfig_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//Ca permet d'indiquer que c'est le service de configurationserver
@EnableConfigServer
//@EnableEurekaClient
@SpringBootApplication
public class BankConfigServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankConfigServiceApplication.class, args);
	}

}
