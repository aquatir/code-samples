package codesample.frameworks.hibernate.one_to_many;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Production is done with a help of big number of {@link codesample.frameworks.hibernate.one_to_many.Worker}
 * but one worker can create only one Production
 */
@Entity
@Table
@SequenceGenerator(name = "production_seq", sequenceName = "production_id_seq")
public class Production {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "production_seq")
    private int id;

    @Column(name = "name")
    private String name;

    /* Map this entity to entity 'production'*/
    @OneToMany(mappedBy = "production")
    private Set<Worker> workers = new HashSet<>();

    public Production() {}
    public Production(String name) {
        this.name = name;
    }

    public void addWorker(Worker worker) {
        workers.add(worker);
    }

    public boolean removeWorker(Worker worker) {
        return workers.remove(worker);
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

    public Set<Worker> getWorkers() {
        return workers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Production)) return false;

        Production other = (Production) o;

        if (id != other.id) return false;
        if (!name.equals(other.name)) return false;
        if (!workers.equals(other.workers)) return false;

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
        String workersString = workers.stream().collect(StringBuilder::new,
                (collected, notCollect) -> collected.append(notCollect).append("\n"),
                (worker1, worker2) -> worker1.append(", ").append(worker2))
                .toString();
        return "Production{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", workers:\n" + workersString +
                '}';
    }
}
