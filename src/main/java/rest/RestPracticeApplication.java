package rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "rest")
public class RestPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestPracticeApplication.class, args);
	}

}
