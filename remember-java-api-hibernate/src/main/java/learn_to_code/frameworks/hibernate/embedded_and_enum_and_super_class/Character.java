package learn_to_code.frameworks.hibernate.embedded_and_enum_and_super_class;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * If you are using superclass which should not be stored in database (Thus, no {@link javax.persistence.Entity}
 * annotation is present) you should use {@link javax.persistence.MappedSuperclass} annotation.
 * All fields defined in this class will be present in subclasses.
 */
@MappedSuperclass
public abstract class Character {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, updatable = false)
    private String name;

    public Character() {}
    public Character(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Character)) return false;

        Character character = (Character) o;

        if (id != character.id) return false;
        if (!name.equals(character.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }
}
