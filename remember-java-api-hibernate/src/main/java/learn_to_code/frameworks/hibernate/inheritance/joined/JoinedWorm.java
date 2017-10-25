package learn_to_code.frameworks.hibernate.inheritance.joined;

import javax.persistence.*;

@Table
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class JoinedWorm {

    @Id
    @GeneratedValue
    private int id;
    private String name;

    public JoinedWorm() {}
    public JoinedWorm(String name) {
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

    @Override
    public String toString() {
        return "JoinedWorm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
