package codesample.kotlin.jwtexample.security.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {

    /**
     * We exclude /auth mapping from spring security which mean that CORS config would not be applied for /auth
     * and thus we have to create non-security CORS config in order for OPTIONS request to work on /auth
     */
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/auth/**").allowedOrigins("http://localhost:4200")
    }
}