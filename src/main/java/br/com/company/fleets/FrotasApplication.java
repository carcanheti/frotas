package br.com.company.fleets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication( scanBasePackages = "br.com.company.fleets")
public class FrotasApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrotasApplication.class, args);
	}

}
