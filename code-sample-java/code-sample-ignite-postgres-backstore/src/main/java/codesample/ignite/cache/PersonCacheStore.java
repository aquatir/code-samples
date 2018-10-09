package codesample.ignite.cache;

import codesample.ignite.entitry.Person;
import codesample.ignite.repository.PersonRepository;
import org.apache.ignite.cache.store.CacheStore;
import org.apache.ignite.lang.IgniteBiInClosure;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

import javax.cache.Cache;
import javax.cache.integration.CacheLoaderException;
import javax.cache.integration.CacheWriterException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Cache Store implementations shows Ignite how it should work with other storages. Postgresql database in this case
 */
@Component
public class PersonCacheStore implements CacheStore<Long, Person>, BeanFactoryAware {

    private static PersonRepository personRepository;

    @Override
    public void loadCache(IgniteBiInClosure<Long, Person> igniteBiInClosure, @Nullable Object... objects) throws CacheLoaderException {
        List<Person> allPersons = personRepository.findAll();
        allPersons.forEach(
                person -> igniteBiInClosure.apply(person.getId(), person)
        );
    }

    @Override
    public Person load(Long id) throws CacheLoaderException {
        Person person = personRepository.findById(id).orElseThrow(() -> new CacheLoaderException("Entry not found"));
        return person;
    }


    @Override
    public Map<Long, Person> loadAll(Iterable<? extends Long> ids) throws CacheLoaderException {
        return personRepository.findAllById((Iterable<Long>) ids).stream()
            .collect(Collectors.toMap(Person::getId, person -> person));
    }


    @Override
    public void write(Cache.Entry<? extends Long, ? extends Person> entry) throws CacheWriterException {
        Person person = entry.getValue();
        person.setId(entry.getKey());
        personRepository.save(person);
    }

    @Override
    public void writeAll(Collection<Cache.Entry<? extends Long, ? extends Person>> collection) throws CacheWriterException {
        List<Person> persons = collection.stream().map(entry -> (Person) entry.getValue())
                .collect(Collectors.toList());
        personRepository.saveAll(persons);
    }

    @Override
    public void delete(Object person) throws CacheWriterException {
        personRepository.delete((Person) person);
    }

    @Override
    public void deleteAll(Collection<?> collection) throws CacheWriterException {
        personRepository.deleteAll((Collection<Person>) collection);
    }

    @Override
    public void sessionEnd(boolean b) throws CacheWriterException {
        // deprecated
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        PersonCacheStore.personRepository = beanFactory.getBean(PersonRepository.class);
    }
}
