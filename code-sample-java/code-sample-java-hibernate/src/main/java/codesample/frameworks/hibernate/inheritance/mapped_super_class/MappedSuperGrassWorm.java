package codesample.frameworks.hibernate.inheritance.mapped_super_class;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class MappedSuperGrassWorm extends MappedSuperWorm {

    private String favouriteBook;

    public MappedSuperGrassWorm() {
    }

    public MappedSuperGrassWorm(String name, String favouriteBook) {
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
        return "MappedSuperGrassWorm{" +
                "id='" + super.getId() + "\'" +
                "name='" + super.getName() + "\'" +
                "favouriteBook='" + favouriteBook + '\'' +
                "} " + super.toString();
    }
}
