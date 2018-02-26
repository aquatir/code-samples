package learn_to_code.frameworks.hibernate.inheritance.single_table;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@DiscriminatorValue("grass_worm")
public class SingleTableGrassWorm extends SingleTableWorm {

    private String favouriteBook;

    public SingleTableGrassWorm() {
    }

    public SingleTableGrassWorm(String name, String favouriteBook) {
        super(name);
        this.favouriteBook = favouriteBook;
    }

    public String getFavouriteBook() {
        return favouriteBook;
    }

    public void setFavouriteBook(String favouriteBook) {
        this.favouriteBook = favouriteBook;
    }

    @Override
    public String toString() {
        return "SingleTableGrassWorm{" +
                "id='" + super.getId() + "\'" +
                "name='" + super.getName() + "\'" +
                "favouriteBook='" + favouriteBook + '\'' +
                "} " + super.toString();
    }
}
