package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication // Annotation for spring boot app. This class will be the main class to run the application.
@EnableWebSecurity
/* This annotation provides security in application. With EnableWebSecurity, every endpoint created in the application is secured
* We will be providing implementation of the security in the configuration file
*/
public class BasicSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicSecurityApplication.class, args);
	}

}
