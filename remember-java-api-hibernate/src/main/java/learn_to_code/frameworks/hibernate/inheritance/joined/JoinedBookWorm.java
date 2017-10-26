package learn_to_code.frameworks.hibernate.inheritance.joined;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class JoinedBookWorm extends JoinedWorm {

    private String favouriteGrass;

    public JoinedBookWorm() {}
    public JoinedBookWorm(String name, String favouriteGrass) {
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
        return "JoinedGrassWorm{" +
                "id='" + super.getId() + "\'" +
                "name='" + super.getName() + "\'" +
                "favouriteBook='" + favouriteGrass + '\'' +
                "} " + super.toString();
    }
}
