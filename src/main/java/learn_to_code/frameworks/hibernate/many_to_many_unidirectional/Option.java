package learn_to_code.frameworks.hibernate.many_to_many_unidirectional;

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

    @Override
    public String toString() {
        return String.format("%3d %20s", id, optionName);
    }
}
