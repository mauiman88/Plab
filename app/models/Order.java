package models;

import play.db.ebean.Model;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by gymate on 2014.10.16..
 */


@Entity
@Table(name = "orders")
public class Order extends Model {

    public static enum OrderStatus {
        NEW("New");

        private final String name;

        OrderStatus(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    @Id
    public Long id;

    @Enumerated(EnumType.STRING)
    public OrderStatus orderStatus;

    @OneToMany( mappedBy = "order")
    public List<Pizza> pizzaList;

    @OneToMany( mappedBy = "order")
    public List<Drink> drinkList;

    @OneToOne( mappedBy = "order", cascade = CascadeType.ALL)
    public Invoice invoice;

}
