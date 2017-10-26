package learn_to_code.frameworks.hibernate.inheritance.table_per_class;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class TablePerClassGrassWorm extends TablePerClassWorm {

    private String favouriteBook;

    public TablePerClassGrassWorm() {
    }

    public TablePerClassGrassWorm(String name, String favouriteBook) {
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
        return "TablePerClassGrassWorm{" +
                "id='" + super.getId() + "\'" +
                "name='" + super.getName() + "\'" +
                "favouriteBook='" + favouriteBook + '\'' +
                "} " + super.toString();
    }
}
