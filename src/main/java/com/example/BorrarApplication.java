package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BorrarApplication {
	
	private Logger salida = LoggerFactory.getLogger(BorrarApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BorrarApplication.class, args);
	}

}
