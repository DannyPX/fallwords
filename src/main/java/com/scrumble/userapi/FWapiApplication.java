package com.scrumble.userapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EntityScan("com/scrumble/userapi/Models")
@SpringBootApplication
@EnableSwagger2
public class FWapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FWapiApplication.class, args);
	}


}

