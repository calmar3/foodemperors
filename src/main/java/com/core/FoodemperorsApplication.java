package com.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan({"endpoint","service"})
@EntityScan("model")
@EnableJpaRepositories("repository")
@EnableMongoRepositories("repository")
public class FoodemperorsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodemperorsApplication.class, args);
	}
}
