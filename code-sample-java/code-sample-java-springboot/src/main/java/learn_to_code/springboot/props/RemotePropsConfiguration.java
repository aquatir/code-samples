package learn_to_code.springboot.props;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RemotePropsConfiguration {

    @Bean
    @RefreshScope
    public RemoteProps remoteProps(@Value("${prop.some_property:default}") String propValue) {
        RemoteProps remoteProps = new RemoteProps();
        remoteProps.setProperty(propValue);
        return remoteProps;
    }
}
