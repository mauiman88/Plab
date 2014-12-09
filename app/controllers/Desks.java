package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Desk;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.JSONUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ujvaricsaba on 12/9/14.
 */
public class Desks extends Controller {
    public static Result list() {
        return ok(views.html.Desk.deskList.render());
    }

    public static Result getDeskList() {
        ObjectNode result = Json.newObject();
        result.put("list", JSONUtils.newArrayNode(Desk.all().stream().map(desk -> desk.toJson()).collect(Collectors.toList())));

        return ok(result);
    }
}
