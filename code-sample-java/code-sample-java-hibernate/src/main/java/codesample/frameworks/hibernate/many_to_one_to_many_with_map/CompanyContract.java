package codesample.frameworks.hibernate.many_to_one_to_many_with_map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class CompanyContract {
    @Id
    @GeneratedValue
    private int id;
    private String name;

    public CompanyContract(){};
    public CompanyContract(String name) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanyContract that = (CompanyContract) o;

        if (id != that.id) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CompanyContract{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
