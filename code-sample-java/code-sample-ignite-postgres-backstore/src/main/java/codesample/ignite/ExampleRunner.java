package codesample.ignite;

import codesample.ignite.entitry.Person;
import codesample.ignite.repository.PersonRepository;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.transactions.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.cache.integration.CacheLoaderException;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ExampleRunner {

    @Autowired
    private Ignite igniteClient;
    @Autowired
    private PersonRepository personRepository;

    @Transactional
    public void runIgnite() {
        IgniteCache<Long, Person> cache = igniteClient.getOrCreateCache("person");


        cache.loadCache(null);

        /* Load all entices to cache and get one of entries; */
        loadEntryFromCacheAndDb(cache);

        /* Save data to cache. Make sure data is saved with DB query */
        saveToCacheCheckInDb(cache, 3L);

        /* Remove value from cache. Make sure it's also removed from DB */
        removeEntryFromCacheCheckIfRemovedFromDb(cache, 3L);
    }

    private void loadEntryFromCacheAndDb(IgniteCache<Long, Person> cache) {
        Person p1FromCache = cache.get(1L);
        Person p1FromDb = personRepository.getOne(1L);
        System.out.println("got person from cache: " + p1FromCache.getId() + " name: " + p1FromCache.getName());
        System.out.println("got person from DB: " + p1FromDb.getId() + " name: " + p1FromCache.getName());
    }

    private void saveToCacheCheckInDb(IgniteCache<Long, Person> cache, Long idToAdd) {
        Person otherPerson = new Person(idToAdd, "Tester 2000");
        cache.put(otherPerson.getId(),  otherPerson);

        Person p3FromCache  = cache.get(idToAdd);
        Person p3FromDb = personRepository.getOne(idToAdd);
        System.out.println("got person from cache: " + p3FromCache.getId() + " name: " + p3FromCache.getName());
        System.out.println("got person from DB: " + p3FromDb.getId() + " name: " + p3FromDb.getName());
    }

    private void removeEntryFromCacheCheckIfRemovedFromDb(IgniteCache<Long, Person> cache, Long idToRemove) {
        cache.remove(idToRemove);
        try {
            Person p3FromCacheAfterRemove = cache.get(idToRemove);
            System.out.println("Person with id 3 found in cache! " + p3FromCacheAfterRemove);
        } catch (TransactionException | CacheLoaderException ex) {
            System.out.println("Person with id 3 not found in cache");
        }
        Optional<Person> p3FromDbAfterRemove = personRepository.findById(idToRemove);
        if (p3FromDbAfterRemove.isPresent()) {
            System.out.println("Person with id 3 found in DB! " + p3FromDbAfterRemove.get());
        } else {
            System.out.println("Person with id 3 not found in DB");
        }

    }
}
