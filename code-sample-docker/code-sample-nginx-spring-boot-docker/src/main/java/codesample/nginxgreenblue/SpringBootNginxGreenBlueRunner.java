package codesample.nginxgreenblue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBootNginxGreenBlueRunner {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootNginxGreenBlueRunner.class, args);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello!";
    }

    @GetMapping("/")
    public String root() {
        return "root!";
    }
}
