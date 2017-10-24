package learn_to_code.frameworks.hibernate.element_collection;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Collection of element is in a way similar to relation with following difference:
 * 1) When you create a relation in DB, you have to provide at least 2 IDs to match data from 2 tables,
 * when dealing with collection you do not have to do it.
 * 2) ElementCollection is used only for data which has a dependant lifecycle (i.g. without pizza we don't need
 * ingredient from which this specific pizza was made from)
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
                (streamOneOutput, streamTwoOutput) -> streamOneOutput.append(streamTwoOutput)).toString();
        return name + " ingredients: " + ingredientsString;
    }

}
