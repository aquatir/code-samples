package codesample.apiversioning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ApiVersioningSpringBootRunner {
    public static void main(String[] args) {
        SpringApplication.run(ApiVersioningSpringBootRunner.class, args);
    }

    @VersionRange("1.6")
    @GetMapping("/")
    public String helloOld() {
        return "Hello 1.6!";
    }

    @VersionRange("1.7")
    @GetMapping("/")
    public String helloNew() {
        return "Hello 1.7!";
    }
}
