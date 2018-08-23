package codesample.springboot.controller;

import codesample.springboot.security.User;
import codesample.springboot.security.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class SecureController {

    private final UserService userService;

    public SecureController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/secure")
    public String helloSecurity() {
        return "hello, dude!";
    }

    @GetMapping("/secure/user")
    public ModelAndView secureUser() {
        ModelAndView mav = new ModelAndView("hello");

        User user = userService.getCurrentAuthenticatedUser();

        mav.addObject("userName", user.getUsername());
        mav.addObject("userPassword", user.getPassword());

        return mav;
    }

    @PostMapping("/secure/user")
    public ModelAndView createUser(@RequestParam String username,
                                   @RequestParam String password) {
        ModelAndView mav = new ModelAndView("hello");
        User user = userService.createNewAndSave(username, password);

        mav.addObject("userName", user.getUsername());
        mav.addObject("userPassword", user.getPassword());

        return mav;
    }
}