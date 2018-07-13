package learn_to_code.springboot.controller;

import learn_to_code.springboot.security.User;
import learn_to_code.springboot.security.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class SecureController {

    private UserService userService;

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

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDetails userDetails = (UserDetails) principal;
        User user = userService.findAndCheckPassword(userDetails);

        mav.addObject("userName", user.getUsername());
        mav.addObject("userPassword", user.getPassword());

        return mav;
    }
}