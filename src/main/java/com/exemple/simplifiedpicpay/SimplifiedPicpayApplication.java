package com.exemple.simplifiedpicpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class SimplifiedPicpayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimplifiedPicpayApplication.class, args);
	}
}
