package learn_to_code.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Collections;
import java.util.List;

/**
 * Security configuration classes go here.
 * Remember: This call will be loaded by ComponentScan by spring-boot. In order to launch spring security at all
 * you need to annotate one of your configuration classes with @EnableWebSecurity.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/home", "/props/**").permitAll() /* Allow this urls to be accesses w/o authentication */
                .anyRequest().authenticated() /* but to not allow any other */
                .and()
                .formLogin()
                .loginPage("/login") /* Where your login form should be. */
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        /* What users are available. Usually this thing will query database for example */
        List<UserDetails> users = Collections.singletonList(User.withUsername("user")
                .password("password")
                .roles("USER")
                .build());

        return new InMemoryUserDetailsManager(users);
    }
}
