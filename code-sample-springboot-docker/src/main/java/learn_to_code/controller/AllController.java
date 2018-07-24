package codesample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AllController {

    @GetMapping("/")
    public String hello() {
        return "Hello, docker!";
    }
}
