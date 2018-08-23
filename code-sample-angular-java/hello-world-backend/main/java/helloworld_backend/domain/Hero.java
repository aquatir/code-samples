package helloworld_backend.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Hero {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
