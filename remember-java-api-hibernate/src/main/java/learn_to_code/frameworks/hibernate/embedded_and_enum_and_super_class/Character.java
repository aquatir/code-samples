package learn_to_code.frameworks.hibernate.embedded_and_enum_and_super_class;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

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
