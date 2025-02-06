package com.bhavana.oauth2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.util.Optional;

@SpringBootApplication
@Slf4j
public class Oauth2Application {

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(Oauth2Application.class);

		Environment env = app.run(args).getEnvironment();
		String appName = Optional.ofNullable(env.getProperty("spring.application.name")).orElse("UnknownApp");
		String serverPort = Optional.ofNullable(env.getProperty("server.port")).orElse("8080");

		log.info("\n----------------------------------------------------------\n\t" +
						"Application '{}' is running! Access URLs:\n\t" +
						"Local: \t\thttp://localhost:{}\n" +
						"----------------------------------------------------------",
				appName, serverPort);

	}

}
