package com.buddhaking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@ComponentScan(basePackages = {"com.buddhaking", "restcontroller", "implement"}) 
public class WebsiteBuddhakingApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsiteBuddhakingApplication.class, args);
	}

}
