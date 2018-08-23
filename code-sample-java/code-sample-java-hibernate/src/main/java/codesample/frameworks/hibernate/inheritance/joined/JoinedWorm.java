package codesample.frameworks.hibernate.inheritance.joined;

import javax.persistence.*;

/**
 * Strategy InheritanceType.JOINED creates one SuperClass entity and a table for each SubClass.
 * SuperClass entity stores data superclass data and subclasses entities store data specific for this superclass.
 * <p/>
 * Pros/cons:
 * + no extra data is stored anywhere. The only extra field apart from classes data is foreign key reference in subclass
 * entity to primary key in superclass entity.
 * + Updates only use a single table and query to update
 * + Is the most straightforward and logical mapping between class structure and DB entries
 * - Select statement requires a join of super entity and sub entity
 * - Insert statement must perform both insert into super entity and into sub entity
 * - Delete statement must delete both super class and sub class entries
 */
@Table
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class JoinedWorm {

    @Id
    @GeneratedValue
    private int id;
    private String name;

    public JoinedWorm() {
    }

    public JoinedWorm(String name) {
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
        return "JoinedWorm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
