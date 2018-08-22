package codesample.kotlin.jwtexample.security.config

import codesample.kotlin.jwtexample.security.AuthExceptionsEntry
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy

@Configuration
@EnableWebSecurity
class SecurityConfig (val authExceptionsEntry: AuthExceptionsEntry)
    : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
                // Route all auth exceptions to this class. See it's comment for more info
                .exceptionHandling().authenticationEntryPoint(authExceptionsEntry)
                .and()

                // Do not store any session info. We are going to authenticate each request with JWT token
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                .authorizeRequests()

                .antMatchers("/auth/**").permitAll()
                .anyRequest().authenticated()
    }

    override fun configure(web: WebSecurity) {
        web
                .ignoring().antMatchers("/h2-console/**/**")
    }
}