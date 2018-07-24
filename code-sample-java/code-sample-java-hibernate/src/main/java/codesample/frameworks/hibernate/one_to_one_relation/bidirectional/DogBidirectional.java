package codesample.frameworks.hibernate.one_to_one_relation.bidirectional;

import javax.persistence.*;

@Entity
@Table(name = "dog_birirectional", schema = "one_to_one")
public class DogBidirectional {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private OwnerBidirectional owner;

    public DogBidirectional() {}
    public DogBidirectional(String name) {
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

    public OwnerBidirectional getOwner() {
        return owner;
    }

    public void setOwner(OwnerBidirectional owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DogBidirectional)) return false;

        DogBidirectional that = (DogBidirectional) o;

        if (id != that.id) return false;
        if (!name.equals(that.name)) return false;
        if (owner != null ? !owner.equals(that.owner) : that.owner != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("%3d %20s %3d %20s", getId(), getName(), getOwner().getId(), getOwner().getName());
    }
}
