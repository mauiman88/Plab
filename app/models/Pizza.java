package models;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.db.ebean.Model;
import play.libs.Json;
import utils.JSONUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Entity
@Table( name = "pizza")
public class Pizza extends Model {

    @Id
    public Long id;

    @ManyToMany
    public List<Topping> topping;

    public String name;

    public Long size;

    public boolean visible;

    @Column(scale = 4, precision = 15)
    public BigDecimal price;

    @ManyToOne
    public Order order;

    public static ArrayNode queryPizzaJson() {
        ArrayNode node = JSONUtils.newArrayNode();
        List<Pizza> pizzaList = find.all();
        for (Pizza pizza : pizzaList) {
            node.add(pizza.toJson());
        }

        return node;
    }

    public ObjectNode toJson() {
        ObjectNode json = Json.newObject();
        json.put("name", name)
            .put("size", size)
            .put("visible",visible)
            .put("price", price);
        return json;
    }

    /* EBEAN */
    public static Finder<Long, Pizza> find = new Finder(Long.class, Pizza.class);

    public static List<Pizza> all(){
        return find.all();
    }

}
