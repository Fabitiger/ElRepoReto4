package com.partyroom.partyroomapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@EntityScan (basePackages = {"com.partyroom.partyroomapi.model"})
@SpringBootApplication
public class PartyroomApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PartyroomApiApplication.class, args);
	}

}

