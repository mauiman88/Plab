package models.cart;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Drink;
import models.Pizza;
import play.db.ebean.Model;
import play.libs.Json;
//jóskagyerekmostpofázzál
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class CartItem extends Model {
    @Id
    public Long id;

    @ManyToOne
    public Cart cart;

    @ManyToOne
    public Drink drink;

    @ManyToOne
    public Pizza pizza;

    public Integer quantity;

    public ObjectNode toJson() {
        ObjectNode json = Json.newObject();

        json.put("drink", drink!=null?drink.toJson():null);
        json.put("pizza", pizza != null ? pizza.toJson() : null);
        json.put("quantity", quantity);

        return json;
    }

    public static boolean isInTheCart() {
        return false;
    }

    public static CartItem findForCart(Long itemId, Cart cart) {
        if(cart == null || itemId == null) {
            return null;
        }
        return null;
    }
}
