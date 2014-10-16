package models;

import play.db.ebean.Model;

import javax.persistence.*;
import javax.persistence.Table;

/**
 * Created by gymate on 2014.10.16..
 */


@Entity
@Table(name = "order")
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


}
