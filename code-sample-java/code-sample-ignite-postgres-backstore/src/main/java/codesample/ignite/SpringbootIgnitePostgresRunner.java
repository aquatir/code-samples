package codesample.ignite;

import codesample.ignite.entitry.Person;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootIgnitePostgresRunner {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootIgnitePostgresRunner.class, args);
    }

    @Bean
    public CommandLineRunner runAtStart() {
        return new CommandLineRunner() {

            @Autowired
            private Ignite igniteClient;


            public void run(String... args)  {
                runIgnite(igniteClient);
            }
        };
    }

    private void runIgnite(Ignite igniteClient) {
        IgniteCache<Long, Person> cache = igniteClient.getOrCreateCache("person");
        cache.loadCache(null);
        Person p = cache.get(1L);
        System.out.println("got person: " + p.getId() + " name: " + p.getName());
    }
}
