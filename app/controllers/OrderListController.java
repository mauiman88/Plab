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
        return ok(views.html.Order.orderList.render());
    }


}