package codesample.kotlin.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class MvcConfig : WebMvcConfigurer {
    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addViewController("/home").setViewName("logout")
        registry.addViewController("/hello").setViewName("hello")
        registry.addViewController("/login").setViewName("login")
        registry.addViewController("/newAccount").setViewName("newAccount")
    }
}