package com.scrumble.userapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EntityScan("com/scrumble/userapi/Models")
@SpringBootApplication
@EnableSwagger2
public class FWapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FWapiApplication.class, args);
	}


}
