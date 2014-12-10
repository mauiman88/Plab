package controllers;

import com.avaje.ebean.Ebean;
import com.google.common.collect.Lists;
import models.Desk;
import models.User;
import org.apache.commons.lang3.StringUtils;
import play.data.Form;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;
import play.db.ebean.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import java.util.List;

import static play.data.Form.form;


public class Application extends Controller {

    public static final String SESSION_USER_ID = "user_id";

    public static Result index() {
        return ok(views.html.index_admin.render());
    }

    public static Result login() {
        return ok(views.html.login.render());
    }

    public static Desk getLocalDesk() {
        String deskNumber = session().get(Products.SESSION_DESK_NUMBER);

        if(!StringUtils.isEmpty(deskNumber)) {
            return Ebean.find(Desk.class).where().eq("deskNumber", Long.parseLong(deskNumber)).findUnique();
        }

        return null;
    }
    public static User getLocalUser() {
        String userId = session().get(SESSION_USER_ID);

        if(!StringUtils.isEmpty(userId)) {
            return Ebean.find(User.class).where().eq("id", Long.parseLong(userId)).findUnique();
        }

        return null;
    }

    public static class LoginForm {
        @Constraints.Email
        @Constraints.Required
        public String email;

        @Constraints.Required
        @Constraints.MinLength(4)
        public String password;

        public User user;

        public List<ValidationError> validate() {
            List<ValidationError> errors = Lists.newArrayList();

            if(StringUtils.isEmpty(email)) {
                errors.add(new ValidationError("email", "error.required"));
            }

            if(StringUtils.isEmpty(password)) {
                errors.add(new ValidationError("password", "error.required"));
            }

            if(!StringUtils.isEmpty(password) && !StringUtils.isEmpty(password)) {
                user = User.authenticate(email, password);
                if (user == null) {
                    errors.add(new ValidationError("auth", "Error during authentication."));
                    errors.add(new ValidationError("auth", "Wrong email or password."));
                }
            }

            return errors.isEmpty()?null:errors;
        }
    }
    public static final Form<LoginForm> LOGIN_FORM = form(LoginForm.class);

    @Transactional
    public static Result saveRegistration(){
        Form<LoginForm> registrationForm = LOGIN_FORM.bind(request().body().asJson());
        if (registrationForm.hasErrors()) {
            return badRequest(registrationForm.errorsAsJson());
        }
        LoginForm userForm = (LoginForm)registrationForm.get();
        return ok();
    }

    @Transactional
    public static Result authenticate() {
        Form<LoginForm> loginForm = LOGIN_FORM.bind(request().body().asJson());
        if(loginForm.hasErrors()) {
            return badRequest(loginForm.errorsAsJson());
        }
        session().put(SESSION_USER_ID, loginForm.get().user.id+"");
        return ok();
    }
}
