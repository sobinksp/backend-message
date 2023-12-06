package dev.tveir.backendmessage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class BackendMessageApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendMessageApplication.class, args);
	}

}
