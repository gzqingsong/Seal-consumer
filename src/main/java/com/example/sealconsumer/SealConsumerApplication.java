package com.example.sealconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class SealConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SealConsumerApplication.class, args);
	}

	@Bean
	HttpHeaders getHttpHeaders(){
		return new HttpHeaders();
	}
}
