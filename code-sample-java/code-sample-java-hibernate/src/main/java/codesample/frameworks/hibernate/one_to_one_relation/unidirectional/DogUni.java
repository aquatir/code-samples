package codesample.frameworks.hibernate.one_to_one_relation.unidirectional;

import javax.persistence.*;

@Entity
@Table(name = "dog_unidirectional", schema = "one_to_one")
public class DogUni {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;

    public DogUni() {}
    public DogUni(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DogUni)) return false;

        DogUni dogUni = (DogUni) o;

        if (id != dogUni.id) return false;
        if (!name.equals(dogUni.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("%3d %20s", getId(), getName());
    }

}
