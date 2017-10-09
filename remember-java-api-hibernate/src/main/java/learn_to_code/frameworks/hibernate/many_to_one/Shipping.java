package learn_to_code.frameworks.hibernate.many_to_one;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Shipping {

    @Id
    @GeneratedValue
    @Column(name="shipping_id")
    private int shippingId;

    @Column
    private LocalDate date;

    /* Note many-to-one relation. Also note that join column name is queried in context of another entity
    * (Item is in fact an entity), so duplicate key names problems is avoided automatically */
    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item;

    @Column
    private int quantity;

    public Shipping(LocalDate date, Item item, int quantity) {
        this.date = date;
        this.item = item;
        this.quantity = quantity;
    }

    public int getShippingId() {
        return shippingId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("%4d %20s %3d %10s", shippingId, item.getItemName(), quantity, date.toString());
    }
}
