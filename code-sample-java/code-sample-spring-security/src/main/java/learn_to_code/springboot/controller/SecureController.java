package learn_to_code.springboot.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@RestController
public class SecureController {

    @GetMapping("/secure")
    public String helloSecurity() {
        return "hello, dude!";
    }

    @GetMapping("/secure/user")
    public ModelAndView secureUser() {
        ModelAndView mav = new ModelAndView("hello");

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String userName = "";
        String userPassword = "";
        Set<GrantedAuthority> authorities = Collections.emptySet();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            userName = userDetails.getUsername();
            userPassword = userDetails.getPassword();
            authorities = new HashSet<>(userDetails.getAuthorities());
        }

        mav.addObject("userName", userName);
        mav.addObject("userPassword", userPassword);
        mav.addObject("authorities", authorities);

        return mav;
    }
}