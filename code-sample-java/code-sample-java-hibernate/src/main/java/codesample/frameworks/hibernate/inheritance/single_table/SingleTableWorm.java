package codesample.frameworks.hibernate.inheritance.single_table;

import javax.persistence.*;


/**
 * Strategy InheritanceType.SINGLE_TABLE create a single table which stores data from all subclasses. Each subclass
 * entry in determined by different value in @DiscriminatorColumn column
 * <p/>
 * Pros/cons:
 * + Select, update, delete, insert statements use a single query.
 * All queries (apart from insert) will be specified by a single 'where' clause and discriminatorColumn value
 * - In order to place multiple different subclasses into a single table, most fields (apart from those defined in superclass)
 * have to be nullable.
 */
@Entity
@Table
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "worm_type", discriminatorType = DiscriminatorType.STRING)
public abstract class SingleTableWorm {

    @Id
    @GeneratedValue
    private int id;
    private String name;

    public SingleTableWorm() {
    }

    public SingleTableWorm(String name) {
        this.name = name;
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SingleTableWorm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
