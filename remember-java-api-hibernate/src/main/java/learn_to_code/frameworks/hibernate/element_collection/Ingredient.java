package learn_to_code.frameworks.hibernate.element_collection;

import org.hibernate.annotations.Parent;

import javax.persistence.Embeddable;

/**
 * Note that we have {@code @Embeddable} annotation here and NO id element.
 * This means that new table will not be created in DB
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
}
