package codesample.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MvcController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }



    /* Login / Logout stuff */

    @GetMapping("newAccount")
    public String newAccount() {
        return "newAccount";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @PostMapping("/logout")
    public String logout() {
        return "logout";
    }
}
