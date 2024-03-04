package com.example.Link;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

//https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-eureka-server.html#spring-cloud-running-eureka-server
@EnableEurekaServer
@SpringBootApplication
public class LinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinkApplication.class, args);
	}

}
