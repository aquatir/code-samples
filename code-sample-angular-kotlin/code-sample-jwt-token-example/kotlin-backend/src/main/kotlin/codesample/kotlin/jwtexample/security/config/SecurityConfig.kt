package codesample.kotlin.jwtexample.security.config

import codesample.kotlin.jwtexample.security.AuthExceptionsEntry
import codesample.kotlin.jwtexample.security.filter.JwtAuthFilter
import codesample.kotlin.jwtexample.security.service.DbUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.BeanIds
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
import org.springframework.web.cors.CorsUtils
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.servlet.config.annotation.CorsRegistration
import org.springframework.web.servlet.config.annotation.CorsRegistry
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
                .cors().configurationSource(corsConfigurationSource())
                .and()

                // Route all auth exceptions to this class. See it's comment for more info
                .exceptionHandling().authenticationEntryPoint(authExceptionsEntry)
                .and()

                // Do not store any session info. We are going to authenticate each request with JWT token
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                // Add custom JWT auth filter (this is the part which actually checks and authenticate user base on JWT
                // token passed
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter::class.java)


                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS,"/auth/**/**").permitAll()
                .antMatchers(HttpMethod.POST,"/auth/**/**").permitAll()
                .antMatchers("/**").authenticated()
    }


    override fun configure(web: WebSecurity) {
        /* This config excludes /h2-console from security filterChain, allowing everyone to access it.
        * Note that adding this URL to HttpSecurity config will yield no result! (And a currently do not know why...
        * Presumably it has something to do with H2 trying to identify you as user which can be authenticated) */
        web
                .ignoring().antMatchers("/h2-console/**/**")
                .and()

                /* Do not use security on token endpoint. Note that this will also exclude CORS configuration
                 * for /auth, so we have to configure CORS for this mapping specifically
                 * which we do in WebConfig class */
                .ignoring().antMatchers(HttpMethod.POST, "/auth/**/**")
                .and()
                .ignoring().antMatchers(HttpMethod.OPTIONS, "/auth/**/**")
                .and()
                .ignoring().antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                )
    }

    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth
                .userDetailsService<UserDetailsService>(dbUserDetailsService)
                .passwordEncoder(passwordEncoderBean())
    }


    /* We need this in order to authenticate user after call to http endpoint
    * in SecurityController */
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }


    /* CORS config for locally deployed angular app */
    @Bean
    fun corsConfigurationSource() : CorsConfigurationSource {
        val configuration = CorsConfiguration().apply {
            allowedOrigins = Arrays.asList("http://localhost:4200")
            allowedMethods = Arrays.asList("GET", "POST", "OPTIONS")

            /* We are going to pass token in AUTHORIZATION header */
            addAllowedHeader(HttpHeaders.AUTHORIZATION)
        }

        return UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration("/**", configuration)
        }
    }


    @Bean
    fun passwordEncoderBean() = BCryptPasswordEncoder()

}