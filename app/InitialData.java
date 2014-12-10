import com.avaje.ebean.Ebean;
import models.Desk;
import models.Drink;
import models.Pizza;
import models.User;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Created by ujvaricsaba on 11/14/14.
 */
public class InitialData {
    private static final Long RANGE_OF_RANDOM = 9999L;
    public static void load() {
        createDesks();
        createPizzas();
        createDrink();
        createAdmin();
    }

    private static void createDrink() {
        if(Ebean.find(Drink.class).findRowCount() < 1) {
            Drink d1 = new Drink();
            d1.name = "Fanta";
            d1.price = new BigDecimal(200);
            d1.description = "0,5l";
            d1.save();

            Drink p2 = new Drink();
            p2.name = "Cola";
            p2.price = new BigDecimal(250);
            p2.description = "0,5l";
            p2.save();
        }
    }

    private static void createPizzas() {
        if(Ebean.find(Pizza.class).findRowCount() < 1) {
            Pizza p1 = new Pizza();
            p1.name = "Magyaros";
            p1.price = new BigDecimal(1200L);
            p1.visible = true;
            p1.size = 42L;
            p1.save();

            Pizza p2 = new Pizza();
            p2.name = "Amerika";
            p2.price = new BigDecimal(1200L);
            p2.visible = true;
            p2.size = 42L;
            p2.save();
        }
    }

    public static void createDesks() {

        if(Ebean.find(Desk.class).findRowCount() < 1) {
            Desk d1 = new Desk();
            d1.deskCode = getRandomNumber();
            d1.deskNumber = 1L;
            d1.save();

            Desk d2 = new Desk();
            d2.deskCode = getRandomNumber();
            d2.deskNumber = 2L;
            d2.save();

            Desk d3 = new Desk();
            d3.deskCode = getRandomNumber();
            d3.deskNumber = 3L;
            d3.save();

            Desk d4 = new Desk();
            d4.deskCode = getRandomNumber();
            d4.deskNumber = 4L;
            d4.save();
        }
    }

    private static Long getRandomNumber() {
        Random r = new Random();
        boolean isAvailable = false;
        long number = (long)(r.nextDouble()*RANGE_OF_RANDOM+1);
        if(Desk.findDeskByCode(number) != null) {
            while(!isAvailable) {
                number = (long)(r.nextDouble()*RANGE_OF_RANDOM);
                if(Desk.findDeskByCode(number) == null) {
                    isAvailable = true;
                }
            }
        }
        return number;
    }

    private static void createAdmin() {
        if(Ebean.find(User.class).where().eq("email","test@example.com").findUnique() == null) {
            User newUser = new User();
            newUser.email = "test@example.com";
            newUser.lastName = "plab";
            newUser.name = "atom";
            newUser.role = User.UserRole.ADMIN;
            newUser.password = User.hashPassword("test");
            newUser.save();
        }
    }
}
