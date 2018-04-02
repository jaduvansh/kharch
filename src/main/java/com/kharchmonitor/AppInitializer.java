package com.kharchmonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppInitializer {
	public static void main(String[] args) {
		String javaHome = System.getenv("KHARCH_DATABASE");
		System.out.println(javaHome);
		SpringApplication.run(AppInitializer.class, args);
	}
}
