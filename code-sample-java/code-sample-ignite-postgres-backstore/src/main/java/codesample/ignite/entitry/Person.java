package codesample.ignite.entitry;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "person_seq", sequenceName = "person_seq", allocationSize = 1)

@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode(of = "name")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
    private Long id;

    private String name;
}
