package learn_to_code.frameworks.hibernate.inheritance.mapped_super_class;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class MappedSuperBookWorm extends MappedSuperWorm {

    private String favouriteGrass;

    public MappedSuperBookWorm() {}
    public MappedSuperBookWorm(String name, String favouriteGrass) {
        super(name);
        this.favouriteGrass = favouriteGrass;
    }

    public String getFavouriteGrass() {
        return favouriteGrass;
    }

    public void setFavouriteGrass(String favouriteGrass) {
        this.favouriteGrass = favouriteGrass;
    }

    @Override
    public String toString() {
        return "MappedSuperBookWorm{" +
                "id='" + super.getId() + "\'" +
                "name='" + super.getName() + "\'" +
                "favouriteBook='" + favouriteGrass + '\'' +
                "} " + super.toString();
    }
}
