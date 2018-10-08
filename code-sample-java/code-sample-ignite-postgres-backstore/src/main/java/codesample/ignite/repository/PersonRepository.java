package codesample.ignite.repository;

import codesample.ignite.entitry.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
