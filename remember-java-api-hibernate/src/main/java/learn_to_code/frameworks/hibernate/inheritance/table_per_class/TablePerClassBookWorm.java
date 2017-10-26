package learn_to_code.frameworks.hibernate.inheritance.table_per_class;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class TablePerClassBookWorm extends TablePerClassWorm {

    private String favouriteGrass;

    public TablePerClassBookWorm() {
    }

    public TablePerClassBookWorm(String name, String favouriteGrass) {
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
        return "TablePerClassBookWorm{" +
                "id='" + super.getId() + "\'" +
                "name='" + super.getName() + "\'" +
                "favouriteBook='" + favouriteGrass + '\'' +
                "} " + super.toString();
    }
}
