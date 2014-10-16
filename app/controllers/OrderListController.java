package controllers;

import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import play.db.ebean.Transactional;


/**
 * Created by gymate on 2014.10.14..
 */
public class OrderListController extends Controller {

    @Transactional
    public static Result list() {
        User user = new User();
        user.email = "test@example.com";
        user.password = User.hashPassword("test");
        user.name = "Test";
        user.save();
        return ok(views.html.Order.orderList.render());
    }


}