package codesample.frameworks.hibernate.element_collection;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Collection of element is in a way similar to relation with following difference:
 * 1) When you create a relation in DB, you have to provide at least 2 IDs to match data from 2 tables,
 * when dealing with collection you do not have to do it.
 * 2) ElementCollection is used only for data which has a dependant lifecycle (i.g. without pizza we don't need
 * ingredient from which this specific pizza was made from)
 *
 * Take note that we must implement hashCode/equals for for Ingredient and Pizza in order to allow hibernate
 * to determine what exactly ingredients should be deleted. Otherwise when deleting pizza, Hibernate will delete this pizza,
 * ALL ingredient and then insert all those ingredient which should not have been deleted.
 *
 * Also note that hashCode/equals is required in Set and Map mappings. If you use List with indexColumn, Hibernate
 * will be using indexes to determine entries which should be deleted
 */
@Table
@Entity
public class Pizza {

    @Id
    @GeneratedValue
    private int id;
    private String name;

    @ElementCollection
    @CollectionTable(name = "pizza_ingredients",
            joinColumns = @JoinColumn(name = "id")) // use id of Pizza entity.
    private Set<Ingredient> ingredients = new HashSet<>();

    public Pizza() {}
    public Pizza(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        String ingredientsString = ingredients.stream().collect(StringBuilder::new,
                (ready, additional) -> ready.append(additional.getName()).append("\n"),
                StringBuilder::append).toString();
        return name + " ingredients: " + ingredientsString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pizza)) return false;

        Pizza pizza = (Pizza) o;

        if (id != pizza.id) return false;
        if (ingredients != null ? !ingredients.equals(pizza.ingredients) : pizza.ingredients != null) return false;
        if (name != null ? !name.equals(pizza.name) : pizza.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (ingredients != null ? ingredients.hashCode() : 0);
        return result;
    }
}
