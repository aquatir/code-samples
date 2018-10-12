package codesample.apiversioning;

import codesample.apiversioning.versionmapping.VersionRange;
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

    @VersionRange(Constants.V_1_6)
    @GetMapping("/")
    public String helloOld() {
        return "Hello 1.6!";
    }

    @VersionRange(Constants.V_1_7)
    @GetMapping("/")
    public String helloNew() {
        return "Hello 1.7!";
    }
}
