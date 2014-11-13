package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Pizza;
import play.data.Form;
import play.data.validation.Constraints;
import play.db.ebean.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import java.math.BigDecimal;

import static play.data.Form.form;

@Transactional
public class Pizzas extends Controller{

    public static class PizzaForm{

        @Constraints.Required
        public String name;

        @Constraints.Required
        public Long size;

        @Constraints.Required
        public BigDecimal price;

    }

    public static final Form<PizzaForm> PIZZA_FORM = form(PizzaForm.class);

    public static Result list() {
        return ok(views.html.Pizza.pizzaList.render());
    }

    public static Result getPizzaList() {
        ObjectNode result = Json.newObject();
        result.put("pizzaList", Pizza.queryPizzaJson());
        return ok(result);
    }

    public static Result addPizza(){
        Form<PizzaForm> pizzaForm = PIZZA_FORM.bindFromRequest(request());
        if(pizzaForm.hasErrors()) {
            return badRequest(pizzaForm.errorsAsJson());
        }

        PizzaForm form = pizzaForm.get();
        Pizza pizza = new Pizza();
        pizza.name = form.name;
        pizza.size = form.size;
        pizza.price = form.price;
        pizza.visible = true;

        pizza.save();

        return ok();
    }


}
