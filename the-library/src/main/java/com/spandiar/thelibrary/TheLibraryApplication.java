package com.spandiar.thelibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TheLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheLibraryApplication.class, args);
	}

}
