package com.kharchmonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppInitializer {
	public static void main(String[] args) {
		System.out.println(System.getenv("KHARCH_DATABASE"));
		SpringApplication.run(AppInitializer.class, args);
	}
}
