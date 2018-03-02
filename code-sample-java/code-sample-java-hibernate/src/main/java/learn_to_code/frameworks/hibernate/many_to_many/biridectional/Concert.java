package learn_to_code.frameworks.hibernate.many_to_many.biridectional;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Concert {

    @Id
    @GeneratedValue
    private int id;
    private String name;

    @ManyToMany(mappedBy = "concerts", cascade = CascadeType.PERSIST)
    private Set<Visitor> visitors;

    public Concert() {}
    public Concert(String name) {
        this.name = name;
        this.visitors = new HashSet<>();
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

    public Set<Visitor> getVisitors() {
        return visitors;
    }

    public void setVisitors(Set<Visitor> visitors) {
        this.visitors = visitors;
    }

    public boolean addVisitor(Visitor visitor) {
        return visitors.add(visitor);
    }

    public boolean deleteVisitor (Visitor visitor) {
        return visitors.remove(visitor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Concert concert = (Concert) o;

        if (this.getId() != concert.getId()) return false;
        if (!this.getName().equals(concert.getName())) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        String visitorsAsString = visitors.stream().collect(StringBuilder::new,
                (alreadyAdded, another) -> alreadyAdded.append("\n").append(another.getName()),
                StringBuilder::append).toString();

        return String.format("%3d %20s %s", getId(), getName(), "visitors:" + visitorsAsString);
    }

}
