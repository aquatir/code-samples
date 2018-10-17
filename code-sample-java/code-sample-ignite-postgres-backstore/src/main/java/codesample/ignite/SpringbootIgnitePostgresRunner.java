package codesample.ignite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootIgnitePostgresRunner {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootIgnitePostgresRunner.class, args);
    }

    @Bean
    public CommandLineRunner runAtStart() {
        return new CommandLineRunner() {

            @Autowired
            ExampleRunner runner;

            public void run(String... args)  {
                runner.runIgnite();
            }
        };
    }
}
