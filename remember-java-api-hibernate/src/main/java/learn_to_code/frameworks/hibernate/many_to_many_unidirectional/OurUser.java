package learn_to_code.frameworks.hibernate.many_to_many_unidirectional;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "some_user")
public class OurUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column
    private String name;

    @ManyToMany
    @JoinTable(name = "enabled_options",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id"))
    private Set<Option> toggledOptions = new HashSet<>();

    public OurUser() {
    }

    public OurUser(String name) {
        this.name = name;
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

    public Set<Option> getToggledOptionsOptions() {
        return toggledOptions;
    }

    public void setToggledOptionsOptions(Set<Option> options) {
        this.toggledOptions = options;
    }

    public boolean addOption(Option option) {
        return toggledOptions.add(option);
    }

    public boolean isToggled(Option option) {
        return toggledOptions.contains(option);
    }

    public boolean removeOption(Option option) {
        return toggledOptions.remove(option);
    }

    @Override
    public String toString() {
        StringBuilder userWithOptions = new StringBuilder();
        for (Option opt : toggledOptions) {
            userWithOptions.append(String.format("%3d %20s %20s", id, name, opt.getOptionName())).append("\n");
        }
        userWithOptions.deleteCharAt(userWithOptions.length() - 1);
        return userWithOptions.toString();
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (toggledOptions != null ? toggledOptions.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OurUser)) return false;

        OurUser other = (OurUser) o;

        if (id != other.id) return false;
        if (!name.equals(other.name)) return false;
        if (!toggledOptions.equals(other.toggledOptions)) return false;

        return true;
    }
}
