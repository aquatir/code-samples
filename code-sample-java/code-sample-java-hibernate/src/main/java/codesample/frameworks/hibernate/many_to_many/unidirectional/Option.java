package codesample.frameworks.hibernate.many_to_many.unidirectional;

import javax.persistence.*;

@Entity
@Table
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name="option_name")
    String optionName;
    
    public Option() {}
    public Option(String optionName) {
        this.optionName = optionName;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("%3d %20s", id, optionName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Option)) return false;

        Option other = (Option) o;

        if (id != other.id) return false;
        if (!optionName.equals(other.optionName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + optionName.hashCode();
        return result;
    }
}
