package com.pedrozanon.practice.project.vitrinni.digital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ContestPracticeProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContestPracticeProjectApplication.class, args);
	}

}
