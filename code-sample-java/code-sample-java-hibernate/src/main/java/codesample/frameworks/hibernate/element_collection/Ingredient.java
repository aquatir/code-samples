package codesample.frameworks.hibernate.element_collection;

import org.hibernate.annotations.Parent;

import javax.persistence.Embeddable;

/**
 * Note that we have {@code @Embeddable} annotation here and NO id element.
 * This means that new table will not be created in DB
 *
 * Take note that we must implement hashCode/equals for for Ingredient and Pizza in order to allow hibernate
 * to determine what exactly ingredients should be deleted.
 */
@Embeddable
public class Ingredient {
    private String name;

    @Parent
    private Pizza pizza;

    public Ingredient() {}
    public Ingredient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingredient)) return false;

        Ingredient that = (Ingredient) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result;
        return result;
    }

}
