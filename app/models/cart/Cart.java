package models.cart;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.Lists;
import play.db.ebean.Model;
import play.libs.Json;
import utils.JSONUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by ujvaricsaba on 2014.09.24..
 */
@Entity
public class Cart extends Model {
    public static final String SESSION_CART_ID = "cart_id";

    @Id
    public Long id;

    @OneToMany(mappedBy = "cart")
    public List<CartItem> items = Lists.newArrayList();

    public static Finder<Long, Cart> find = new Finder(Long.class, Cart.class);

    public static List<Cart> all(){ return find.all(); }

    public ObjectNode toJson() {
        ObjectNode json = Json.newObject();
        ArrayNode node = JSONUtils.newArrayNode();
        for (CartItem item : items) {
            node.add(item.toJson());
        }

        json.put("items", node);

        return json;
    }

    public BigDecimal calculateItemsTotalPrice() {
        BigDecimal price = new BigDecimal(0);
        for (CartItem item : items) {
        }
        return price;
    }

    public void addItem(CartItem item) {
        item.save();
        items.add(item);
    }

    public void deleteItems() {
        for (CartItem item : items) {
            item.delete();
        }
    }


}
