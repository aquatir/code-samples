package codesample.frameworks.hibernate.embedded_and_enum_and_super_class;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * {@link javax.persistence.Embeddable} annotation states that this class will not be stored as entity, but
 * will appear as part of another entity ({@link Hero} in this example).
 *
 * Also note {@link javax.persistence.Enumerated} which can be used to store java enum types in entities.
 */
@Embeddable
public class Power {
    private String powerName;

    @Enumerated(EnumType.STRING)
    private PowerLevel powerLevel;

    public Power() {}
    public Power(String powerName, PowerLevel powerLevel) {
        this.powerName = powerName;
        this.powerLevel = powerLevel;
    }

    public String getPowerName() {
        return powerName;
    }

    public void setPowerName(String powerName) {
        this.powerName = powerName;
    }

    public PowerLevel getPowerLevel() {
        return powerLevel;
    }

    public void setPowerLevel(PowerLevel powerLevel) {
        this.powerLevel = powerLevel;
    }
}
