package models;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.db.ebean.Model;
import play.libs.Json;
import utils.JSONUtils;

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
    public List<OrderItem> orderItems;

    @OneToOne( mappedBy = "order", cascade = CascadeType.ALL)
    public Invoice invoice;

    public static ArrayNode queryOrderJson() {
        ArrayNode node = JSONUtils.newArrayNode();
        List<Order> orderList = find.all();
        for (Order order : orderList) {
            node.add(order.toJson());
        }

        return node;
    }

    public ObjectNode toJson() {
        ObjectNode json = Json.newObject();
        json.put("id", id)
                .put("orderStatus", orderStatus.getName());
        return json;
    }

    /* EBEAN */
    public static Finder<Long, Order> find = new Finder(Long.class, Order.class);

    public static List<Order> all(){
        return find.all();
    }
}
