package learn_to_code.frameworks.hibernate;

import javax.persistence.*;

@Entity
@Table
public class Towns {

    public Towns() {
    }
    public Towns(String townName, int distance) {
        setTownName(townName);
        setDistance(distance);
    }

    @Id
    @GeneratedValue
    @Column(name="town_id")
    private int townId;

    @Column(name="town_name")
    private String townName;

    @Column(name="distance")
    private int distance;


    public int getTownId() {
        return townId;
    }

    public void setTownId(int townId) {
        this.townId = townId;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
