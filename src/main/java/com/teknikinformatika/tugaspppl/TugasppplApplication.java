package com.teknikinformatika.tugaspppl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class TugasppplApplication {

	public static void main(String[] args) {
		SpringApplication.run(TugasppplApplication.class, args);

	}
}
