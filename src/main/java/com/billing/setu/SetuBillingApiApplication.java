package com.billing.setu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import static com.billing.setu.configuration.ApplicationConfiguration.dropExisitingTables;

@SpringBootApplication
public class SetuBillingApiApplication {

	public static void main(String[] args) {
		dropExisitingTables();
		ConfigurableApplicationContext ctx =
				SpringApplication.run(SetuBillingApiApplication.class, args);
	}

}
