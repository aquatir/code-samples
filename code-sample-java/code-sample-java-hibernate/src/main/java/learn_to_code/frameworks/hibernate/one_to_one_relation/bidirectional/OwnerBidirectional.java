package learn_to_code.frameworks.hibernate.one_to_one_relation.bidirectional;

import javax.persistence.*;

@Entity
@Table(name = "owner_bidirectional", schema = "one_to_one")
public class OwnerBidirectional {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dog_id")
    private DogBidirectional dog;

    public OwnerBidirectional() {}
    public OwnerBidirectional(String name, DogBidirectional dog) {
        this.name = name;
        this.dog = dog;
        this.dog.setOwner(this);
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

    public DogBidirectional getDog() {
        return dog;
    }

    public void setDog(DogBidirectional dog) {
        this.dog = dog;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OwnerBidirectional)) return false;

        OwnerBidirectional that = (OwnerBidirectional) o;

        if (id != that.getId()) return false;
        if (getDog().getId() != that.getDog().getId()) return false;
        if (!name.equals(that.getName())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + dog.getId();
        result = 31 * result + dog.getName().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("%3d %20s %3d %20s", getId(), getName(), getDog().getId(), getDog().getName());
    }
}
