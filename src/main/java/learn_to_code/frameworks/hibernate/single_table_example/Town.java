package learn_to_code.frameworks.hibernate.single_table_example;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table
public class Town {

    public Town() {
    }

    public Town(String townName, int distance, String biggestRiver) {
        setTownName(townName);
        setDistance(distance);
        setBiggestRiver(biggestRiver);
    }

    @Id
    @GeneratedValue
    @Column(name="town_id")
    private int townId;

    @NaturalId
    @Column(name="town_name")
    private String townName;

    @NaturalId
    @Column(name="biggest_river")
    private String biggestRiver;

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

    public String getBiggestRiver() {
        return biggestRiver;
    }

    public void setBiggestRiver(String biggestRiver) {
        this.biggestRiver = biggestRiver;
    }

    @Override
    public String toString() {
        return String.format("%4d %20s %6d %20s", townId, townName, distance, biggestRiver );
    }


}
