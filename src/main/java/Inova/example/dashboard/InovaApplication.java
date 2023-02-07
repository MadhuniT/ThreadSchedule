package Inova.example.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class InovaApplication {

	public static void main(String[] args) {
		SpringApplication.run(InovaApplication.class, args);
	}

}
