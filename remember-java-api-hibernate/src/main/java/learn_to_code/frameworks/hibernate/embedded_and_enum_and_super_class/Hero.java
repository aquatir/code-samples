package learn_to_code.frameworks.hibernate.embedded_and_enum_and_super_class;

import javax.persistence.*;

/**
 * {@link learn_to_code.frameworks.hibernate.embedded_and_enum_and_super_class.Character} is defined with
 * {@link javax.persistence.MappedSuperclass} annotations thus all field from that class will be present in this entity.
 *
 * Note that {@link Power} field is embedded. It means that all fields from Power object will be stored in
 * this entity.
 */
@Entity
@Table
public class Hero extends Character {

    @Embedded
    private Power power;

    public Hero() {
        super();
    }

    public Hero(String name, Power power) {
        super(name);
        this.power = power;
    }

    public Power getPower() {
        return power;
    }

    public void setPower(Power power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return String.format("%3d %20s %20s %20s", getId(), getName(), getPower().getPowerName(), getPower().getPowerLevel().toString());
    }
}
