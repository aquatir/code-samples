package codesample.frameworks.hibernate.many_to_many.biridectional;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Visitor {

    @Id
    @GeneratedValue
    private int id;
    private String name;


    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "concert_visitor",
            joinColumns = {@JoinColumn(name="visitor_id")},
            inverseJoinColumns = {@JoinColumn(name = "concert_id")})
    private Set<Concert> concerts;

    public Visitor() {}
    public Visitor(String name) {
        this.name = name;
        this.concerts = new HashSet<>();
    }

    private int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Concert> getConcerts() {
        return concerts;
    }

    public void setConcerts(Set<Concert> concerts) {
        this.concerts = concerts;
    }

    public void addConcert(Concert concert) {
        concert.addVisitor(this);
        concerts.add(concert);
    }

    public boolean deleteConcert (Concert concert) {
        concert.deleteVisitor(this);
        return concerts.remove(concert);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Visitor visitor = (Visitor) o;

        if (this.getId() != visitor.getId()) return false;
        if (!this.getName().equals(visitor.getName())) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }

    public String toString() {
        String concertsAsString = concerts.stream().collect(StringBuilder::new,
                (alreadyAdded, another) -> alreadyAdded.append("\n").append(another.getName()),
                StringBuilder::append).toString();

        return String.format("%3d %20s %s", getId(), getName(), "concerts:" + concertsAsString);
    }

}
