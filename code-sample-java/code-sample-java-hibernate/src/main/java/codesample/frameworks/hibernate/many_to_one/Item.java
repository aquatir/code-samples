package codesample.frameworks.hibernate.many_to_one;


import javax.persistence.*;

@Entity
@Table
public class Item {

    @Id
    @GeneratedValue
    @Column(name="item_id")
    private int itemId;

    @Column
    private String itemName;

    public Item(String itemName) {
        this.itemName = itemName;
    }

    public int getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String toString() {
        return String.format("%4d %20s", itemId, itemName);
    }
}
