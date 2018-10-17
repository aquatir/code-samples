package codesample.ignite;

import codesample.ignite.entitry.Person;
import codesample.ignite.repository.PersonRepository;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteAtomicSequence;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.transactions.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.cache.integration.CacheLoaderException;
import javax.transaction.Transactional;
import java.util.Optional;

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
            @Autowired
            private PersonRepository personRepository;

            public void run(String... args)  {
                runIgnite(igniteClient, personRepository);
            }
        };
    }

    private void runIgnite(Ignite igniteClient, PersonRepository personRepository) {
        IgniteCache<Long, Person> cache = igniteClient.getOrCreateCache("person");

        /* Load all entices to cache and get one of entries; */
        cache.loadCache(null);
        Person p = cache.get(1L);
        System.out.println("got person: " + p.getId() + " name: " + p.getName());

        /* Save data to cache. Make sure data is saved with DB query */
        Long newId = 3L;
        Person otherPerson = new Person(newId, "Tester 2000");
        cache.put(otherPerson.getId(),  otherPerson);

        Person pp  = cache.get(newId);
        System.out.println("got person: " + p.getId() + " name: " + pp.getName());

        /* Remove value from cache. Make sure it's also removed from DB */
        cache.remove(newId);
        try {
            Person ppp = cache.get(newId);
            System.out.println("Person with id 3 found! " + ppp);
        } catch (TransactionException | CacheLoaderException ex) {
            System.out.println("Person with id 3 not found");
        }

    }
}
