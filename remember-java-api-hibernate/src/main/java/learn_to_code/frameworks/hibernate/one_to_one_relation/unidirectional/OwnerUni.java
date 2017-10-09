package learn_to_code.frameworks.hibernate.one_to_one_relation.unidirectional;

import javax.persistence.*;

@Entity
@Table
public class OwnerUni {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dog_id")
    private DogUni dog;

    @Column(nullable = false)
    private String name;

    public OwnerUni() {}
    public OwnerUni(String name, DogUni dog) {
        this.name = name;
        this.dog = dog;
    }

    public int getId() {
        return id;
    }

    public DogUni getDog() {
        return dog;
    }

    public void setDog(DogUni dog) {
        this.dog = dog;
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
        if (!(o instanceof OwnerUni)) return false;

        OwnerUni ownerUni = (OwnerUni) o;

        if (id != ownerUni.id) return false;
        if (dog != null ? !dog.equals(ownerUni.dog) : ownerUni.dog != null) return false;
        if (!name.equals(ownerUni.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (dog != null ? dog.hashCode() : 0);
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("%3d %20s %3d %20s", id, name, getDog().getId(), getDog().getName());
    }
}
