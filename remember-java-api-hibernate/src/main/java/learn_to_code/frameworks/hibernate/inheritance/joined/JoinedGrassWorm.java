package learn_to_code.frameworks.hibernate.inheritance.joined;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class JoinedGrassWorm extends JoinedWorm {

    private String favouriteBook;

    public JoinedGrassWorm(){}
    public JoinedGrassWorm(String name, String favouriteBook) {
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
        return "JoinedGrassWorm{" +
                "id='" + super.getId() + "\'" +
                "name='" + super.getName() + "\'" +
                "favouriteBook='" + favouriteBook + '\'' +
                "} " + super.toString();
    }
}
