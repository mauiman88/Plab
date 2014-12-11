package controllers;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Drink;
import models.Pizza;
import models.cart.Cart;
import models.cart.CartItem;
import org.apache.commons.lang3.StringUtils;
import play.data.DynamicForm;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.db.ebean.Transactional;

import java.util.List;


/**
 * Created by gymate on 2014.10.14..
 */
@Transactional
public class Orders extends Controller {

    public static Result list() {
        return ok(views.html.Order.orderList.render());
    }

    public static Result addItemToCart() {
        String cartIdString = session().get(Cart.SESSION_CART_ID);
        Cart cart = null;
        if(!StringUtils.isEmpty(cartIdString)) {
            cart = Ebean.find(Cart.class).where().eq("id", Long.parseLong(cartIdString)).findUnique();
        } else {
            cart = new Cart();
            cart.save();
            session().put(Cart.SESSION_CART_ID, cart.id+"");
        }

        DynamicForm form = DynamicForm.form().bindFromRequest(request());
        String pizzaIdString = form.get("pizzaId");
        String drinkIdString = form.get("drinkId");
        String quantityString = form.get("quantity");

        List<CartItem> items = Ebean.find(CartItem.class).where().eq("cart", cart).findList();
        for (CartItem item : items) {
            if(!StringUtils.isEmpty(pizzaIdString) && item.pizza != null && item.pizza.id.equals(Long.parseLong(pizzaIdString))) {
                form.reject("cartError", "Already added");
                return badRequest(form.errorsAsJson());
            }
            if(!StringUtils.isEmpty(drinkIdString) && item.drink != null && item.drink.id.equals(Long.parseLong(drinkIdString))) {
                form.reject("cartError", "Already added");
                return badRequest(form.errorsAsJson());
            }
        }

        CartItem cartItem = new CartItem();

        if(!StringUtils.isEmpty(pizzaIdString)) {
            Pizza pizza = Ebean.find(Pizza.class).where().eq("id", Long.parseLong(pizzaIdString)).findUnique();
            cartItem.pizza = pizza;
        }
        if(!StringUtils.isEmpty(drinkIdString)) {
            Drink drink = Ebean.find(Drink.class).where().eq("id", Long.parseLong(drinkIdString)).findUnique();
            cartItem.drink = drink;
        }
        cartItem.quantity = Integer.parseInt(quantityString);
        cartItem.cart = cart;
        cartItem.save();
        cart.items.add(cartItem);
        cart.save();

        return ok(cart.toJson());
    }

    public static Result removeItemFromCart() {
        String cartIdString = session().get(Cart.SESSION_CART_ID);
        Cart cart = Ebean.find(Cart.class).where().eq("id", Long.parseLong(cartIdString)).findUnique();
        List<CartItem> items = Ebean.find(CartItem.class).where().eq("cart", cart).findList();

        DynamicForm form = DynamicForm.form().bindFromRequest(request());
        String pizzaIdString = form.get("pizzaId");
        String drinkIdString = form.get("drinkId");


        for (CartItem item : items) {
            if(!StringUtils.isEmpty(pizzaIdString) && item.pizza != null && item.pizza.id.equals(Long.parseLong(pizzaIdString))) {
                item.delete();
            }
            if(!StringUtils.isEmpty(drinkIdString) && item.drink != null && item.drink.id.equals(Long.parseLong(drinkIdString))) {
                item.delete();
            }
        }
        return ok();
    }

    public static Result getCart() {
        ObjectNode json = Json.newObject();
        if(!StringUtils.isEmpty(session().get(Cart.SESSION_CART_ID))) {
            Long cartId = Long.parseLong(session().get(Cart.SESSION_CART_ID));
            Cart cart = Ebean.find(Cart.class).where().eq("id", cartId).findUnique();
            json = cart.toJson();
        }
        return ok(json);
    }
}