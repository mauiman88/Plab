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
@Table(name = "drinks")
public class Drink extends Model {
    @Id
    public Long id;

    public String name;

    public String description;

    @Column(scale = 4, precision = 15)
    public BigDecimal price;

    @ManyToOne
    public Order order;

    public static ArrayNode queryDrinkJson() {
        ArrayNode node = new JSONUtils().newArrayNode();
        List<Drink> drinkList = find.all();
        for( Drink drink: drinkList){
            node.add(drink.toJson());
        }
        return node;
    }

    public ObjectNode toJson() {
        ObjectNode json = Json.newObject();
        json.put("name", name)
                .put("description", description)
                .put("price", price);
        return json;
    }

    /* EBEAN */
    public static Finder<Long, Drink> find = new Finder(Long.class, Drink.class);
    public static List<Drink> all(){ return find.all(); }
}
