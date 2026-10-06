package dev.krish.thermogrid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ThermoGridApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThermoGridApplication.class, args);
	}

}
