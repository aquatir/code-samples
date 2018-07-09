package learn_to_code.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure")
public class SecureController {

    @GetMapping("/")
    public String helloSecurity() {
        return "hello, dude!";
    }
}