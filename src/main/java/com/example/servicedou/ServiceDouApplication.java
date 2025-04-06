package com.example.servicedou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient

public class ServiceDouApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServiceDouApplication.class, args);
	}
}
