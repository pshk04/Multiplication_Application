package microservices.book.multiplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MultiplicationApplication {

	public static void main(String[] args) {
        String version = org.springframework.boot.SpringBootVersion.getVersion();
        System.out.println("Spring Boot Version: " + version);
		SpringApplication.run(MultiplicationApplication.class, args);
	}



}
