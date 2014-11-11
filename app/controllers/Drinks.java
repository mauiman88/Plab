package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Drink;
import play.data.Form;
import play.data.validation.Constraints;
import play.db.ebean.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import java.math.BigDecimal;

import static play.data.Form.form;

/**
 * Created by gymate on 2014.11.11..
 */
@Transactional
public class Drinks extends Controller {

    public static class DrinkForm{
        @Constraints.Required
        public String name;

        @Constraints.Required
        public BigDecimal price;

        @Constraints.Required
        public String description;
    }
    public static final Form<DrinkForm> DRINK_FORM = form(DrinkForm.class);

    public static Result list() { return ok(views.html.Drink.drinkList.render()); }

    public static Result getDrinkList() {
        ObjectNode result = Json.newObject();
        result.put("drinkList", Drink.queryDrinkJson());
        return ok(result);
    }

    public static Result addDrink(){
        Form<DrinkForm> drinkForm = DRINK_FORM.bindFromRequest(request());
        if(drinkForm.hasErrors()) {
            return badRequest(drinkForm.errorsAsJson());
        }

        DrinkForm form = drinkForm.get();
        Drink drink = new Drink();
        drink.name = form.name;
        drink.description = form.description;
        drink.price = form.price;

        drink.save();

        return ok();
    }

}
