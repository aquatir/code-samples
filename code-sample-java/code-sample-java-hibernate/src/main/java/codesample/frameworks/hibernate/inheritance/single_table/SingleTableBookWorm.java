package codesample.frameworks.hibernate.inheritance.single_table;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@DiscriminatorValue("book_worm")
public class SingleTableBookWorm extends SingleTableWorm {
    private String favouriteGrass;

    public SingleTableBookWorm() {
    }

    public SingleTableBookWorm(String name, String favouriteGrass) {
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
        return "SingleTableBookWorm{" +
                "id='" + super.getId() + "\'" +
                "name='" + super.getName() + "\'" +
                "favouriteBook='" + favouriteGrass + '\'' +
                "} " + super.toString();
    }
}
