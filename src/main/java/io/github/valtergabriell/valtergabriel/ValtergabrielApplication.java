package io.github.valtergabriell.valtergabriel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class ValtergabrielApplication {

	public static void main(String[] args) {
		SpringApplication.run(ValtergabrielApplication.class, args);
	}

}
