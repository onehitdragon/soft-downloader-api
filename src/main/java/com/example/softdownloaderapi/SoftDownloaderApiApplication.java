package com.example.softdownloaderapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class SoftDownloaderApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoftDownloaderApiApplication.class, args);
	}

}
