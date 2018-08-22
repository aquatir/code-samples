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
                .antMatchers("/**").authenticated()
    }

    override fun configure(web: WebSecurity) {
        /* This config excludes /h2-console from security filterChain, allowing everyone to access it.
        * Note that adding this URL to HttpSecurity config will yield no result! (And a currently do not know why...
        * Presumably it has something to do with H2 trying to identify you as user which can be authenticated) */
        web
                .ignoring().antMatchers("/h2-console/**/**")
    }
}