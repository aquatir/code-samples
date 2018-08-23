package codesample.kotlin.jwtexample.security.config

import codesample.kotlin.jwtexample.security.AuthExceptionsEntry
import codesample.kotlin.jwtexample.security.filter.JwtAuthFilter
import codesample.kotlin.jwtexample.security.service.DbUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import java.util.*

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true // Allow usage of Pre and Post authorize annotations
)
class SecurityConfig (val authExceptionsEntry: AuthExceptionsEntry,
                      val dbUserDetailsService: DbUserDetailsService,
                      val jwtAuthFilter: JwtAuthFilter)
    : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http

                // Configure CORS to allow assess from locally deployed angular app
                .cors().configurationSource(corsConfigurationSource())
                .and()

                // Route all auth exceptions to this class. See it's comment for more info
                .exceptionHandling().authenticationEntryPoint(authExceptionsEntry)
                .and()

                // Do not store any session info. We are going to authenticate each request with JWT token
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter::class.java)

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

    @Bean
    fun corsConfigurationSource() : CorsConfigurationSource {
        val configuration = CorsConfiguration().apply {
            allowedOrigins = Arrays.asList("http://localhost:8080")
            allowedMethods = Arrays.asList("GET", "POST")
        }

        return UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration("/**", configuration)
        }
    }

    @Bean
    fun passwordEncoderBean() = BCryptPasswordEncoder()

    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth
                .userDetailsService<UserDetailsService>(dbUserDetailsService)
                .passwordEncoder(passwordEncoderBean())
    }




}