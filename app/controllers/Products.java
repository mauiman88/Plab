package controllers;

import com.avaje.ebean.Ebean;
import models.Desk;
import models.Drink;
import models.Pizza;
import org.apache.commons.lang3.StringUtils;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.db.ebean.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;


@Transactional
public class Products extends Controller {

    public static final String SESSION_DESK_NUMBER = "desk_number";

    public static Result productList() {
        List<Pizza> pizzaList = Pizza.all();
        List<Drink> drinkList = Drink.all();

        return ok(views.html.Client.Desktop.productList.render(pizzaList, drinkList));
    }

    public static Result authenticateDesk() {
        DynamicForm dynamicForm = DynamicForm.form().bindFromRequest(request());
        String deskNumberAsString = dynamicForm.get("deskNumber");
        String deskCodeAsString = dynamicForm.get("deskCode");

        if(StringUtils.isEmpty(deskNumberAsString) || StringUtils.isEmpty(deskCodeAsString)) {
            if(StringUtils.isEmpty(deskNumberAsString)) dynamicForm.reject(new ValidationError("deskNumber", "error.required"));
            if(StringUtils.isEmpty(deskCodeAsString)) dynamicForm.reject(new ValidationError("deskCode", "error.required"));
            return badRequest(dynamicForm.errorsAsJson());
        }

        Long deskNumber = Long.parseLong(deskNumberAsString);
        Long deskCode = Long.parseLong(deskCodeAsString);
        Desk desk = Desk.authenticateDesk(deskNumber, deskCode);

        if(desk == null) {
            return forbidden();
        }
        desk.deskState = Desk.DeskState.BEING_USED;
        desk.update();
        session().put(SESSION_DESK_NUMBER, deskNumberAsString);

        return ok();
    }

    public static Result leaveDesk() {
        if(!StringUtils.isEmpty(session().get(SESSION_DESK_NUMBER))) {
            Desk desk = Ebean.find(Desk.class).where().eq("id", Long.parseLong(session().get(SESSION_DESK_NUMBER))).findUnique();
            desk.deskState = Desk.DeskState.NEW;
            desk.save();
            session().remove(SESSION_DESK_NUMBER);
        }
        return productList();
    }
}
