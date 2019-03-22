package codesample.springboot.propconfig.reloadable_message_source;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration

public class ReloadableMessageSourceConfig {

    @Bean
    public ReloadableResourceBundleMessageSource myReloadableProperties() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setCacheSeconds(2); // read properties every 2 seconds
        messageSource.setBasename("classpath:props/reloadable");
        messageSource.setUseCodeAsDefaultMessage(true);

        return messageSource;
    }
}
