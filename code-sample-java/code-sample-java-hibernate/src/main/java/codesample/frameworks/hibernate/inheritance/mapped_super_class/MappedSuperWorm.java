package codesample.frameworks.hibernate.inheritance.mapped_super_class;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * MappedSuperClass is not an inheritance strategy, but nevertheless it's an extremely useful instrument.
 * Classes marker with {@link javax.persistence.MappedSuperclass} annotation will not be present as entities in DB
 * however all fields defined in this classes will be present in entities which use MappedSuperClass entity as super class
 * (E.g.: in this example 'id' and 'name' field will be present in any subclass of MappedSuperWorm.)
 * <p/>
 * Pros/cons:
 * + Select, update, delete, insert statements use a single query.
 * - Entities has no notion of inheritance, thus queries to superclass will not yield any result.
 * (e.g. 'session.createQuery("from MappedSuperWorm")' will throw an error.
 */
@MappedSuperclass
public class MappedSuperWorm {
    @Id
    @GeneratedValue
    private int id;
    private String name;

    public MappedSuperWorm() {
    }

    public MappedSuperWorm(String name) {
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
