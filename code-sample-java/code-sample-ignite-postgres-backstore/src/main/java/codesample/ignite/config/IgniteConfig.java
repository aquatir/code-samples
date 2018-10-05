package codesample.ignite.config;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteException;
import org.apache.ignite.Ignition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IgniteConfig {

    @Bean
    Ignite igniteClient() throws IgniteException {

        /* This will search claspath and $IGNITE_HOME s*/
        return Ignition.start("ignite/client-ignite.xml");
    }
}
