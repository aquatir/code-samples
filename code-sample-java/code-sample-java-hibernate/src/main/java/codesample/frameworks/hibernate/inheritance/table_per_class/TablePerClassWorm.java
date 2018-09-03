package codesample.frameworks.hibernate.inheritance.table_per_class;

import javax.persistence.*;

/**
 * Strategy InheritanceType.TABLE_PER_CLASS create a table for each class in inheritance hierarchy INCLUDING
 * superclass. Entities keep their notion of inheritance thus query to super class WILL have an effect on all subclasses
 * (be it select or delete), BUT this will only work if super class and sub classes DO NOT HAVE repeating primary keys.
 * Use shared primary key generator if supported by DB when dealing with this relation
 * <p/>
 * Pros/cons:
 * + Select, update, delete, insert statements can use a single table if asking for sub classes
 * + Select, update, delete, insert statements can will join tables if asking for super class
 * - An table for super class entity WILL always
 */
@Entity
@Table
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class TablePerClassWorm {
    @Id
    @GeneratedValue
    private int id;
    private String name;

    public TablePerClassWorm() {
    }

    public TablePerClassWorm(String name) {
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
        return "TablePerClassWorm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
