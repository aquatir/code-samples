package learn_to_code.frameworks.hibernate.crud_operations_example;

import org.hibernate.annotations.NaturalId;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

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
    @Column(name="id")
    private int Id;

    @NaturalId
    @Column(name="town_name")
    private String townName;

    @NaturalId
    @Column(name="biggest_river")
    private String biggestRiver;

    @Column(name="distance")
    private int distance;



    public int getId() {
        return Id;
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
        return String.format("%4d %20s %6d %20s", Id, townName, distance, biggestRiver );
    }


}
