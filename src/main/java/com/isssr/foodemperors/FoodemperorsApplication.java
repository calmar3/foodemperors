package com.isssr.foodemperors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan({"com.isssr.foodemperors.endpoint"})
@EntityScan("com/isssr/foodemperors/model")
@EnableJpaRepositories("com.isssr.foodemperors.repository")
@EnableMongoRepositories("com.isssr.foodemperors.repository")
public class FoodemperorsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodemperorsApplication.class, args);
	}
}
