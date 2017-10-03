package learn_to_code.frameworks.hibernate.one_to_many;

import javax.persistence.*;

@Entity
@Table
@SequenceGenerator(name = "worker_seq", sequenceName = "worker_id_seq")
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "worker_seq")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn
    private Production production;

    public Worker() {
    }

    public Worker(String worker, Production production) {
        this.name = worker;
        this.production = production;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Production getProduction() {
        return production;
    }

    public void setProduction(Production production) {
        this.production = production;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Worker)) return false;

        Worker worker1 = (Worker) o;

        if (id != worker1.id) return false;
        if (!production.equals(worker1.production)) return false;
        if (!name.equals(worker1.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + production.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", production=" + production.getName() +
                '}';
    }
}
