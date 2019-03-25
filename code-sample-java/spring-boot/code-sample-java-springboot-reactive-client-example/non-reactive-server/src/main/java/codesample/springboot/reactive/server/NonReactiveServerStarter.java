package codesample.springboot.reactive.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NonReactiveServerStarter {

	public static void main(String[] args) {
		SpringApplication.run(NonReactiveServerStarter.class, args);
	}

}
