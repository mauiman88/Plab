package models;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.libs.Crypto;
import play.libs.Json;
import utils.JSONUtils;

import javax.persistence.*;
import java.util.List;

/**
 * Created by gymate on 2014.09.30..
 */

@Entity
@Table(name = "users")
public class User extends Model {


    public static enum UserRole {
        ADMIN("Admin");

        private final String name;

        UserRole(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }


    @Id
    public Long id;

    @Constraints.Email
    @Column(columnDefinition = "varchar", unique = true)
    public String email;

    public String password;

    @Column(columnDefinition = "varchar")
    public String name;

    @Column(columnDefinition = "varchar")
    public String firstName;

    @Column(columnDefinition = "varchar")
    public String lastName;

    @Enumerated(EnumType.STRING)
    public UserRole role;


    public static User authenticate(String email, String password) {
        if(Ebean.find(User.class).where().eq("email","test@example.com").findUnique() == null) {
            User newUser = new User();
            newUser.email = "test@example.com";
            newUser.lastName = "plab";
            newUser.name = "atom";
            newUser.password = hashPassword("test");
            newUser.save();
        }
        User user = Ebean.find(User.class).where().eq("email", email).findUnique();
        if( user != null && hashPassword(password).equals(user.password)) {
            return user;
        }
        return null;
    }

    public static ObjectNode queryUsers() {
        List<User> users = new Finder(String.class, User.class).all();
        ArrayNode userArray = JSONUtils.newArrayNode();
        for (User user : users) {
            userArray.add(user.toJson());
        }

        ObjectNode result = Json.newObject();
        result.put("list", userArray);
        return result;
    }

    public ObjectNode toJson() {
        ObjectNode json = Json.newObject()
                .put("name", name)
                .put("email", email)
                .put("role", role==null?"undefined":role.getName())
                .put("password", password);
        return json;
    }

    public static String hashPassword(String password) {
        return Crypto.sign(password);
    }
}
