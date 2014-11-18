import com.avaje.ebean.Ebean;
import models.Desk;

import java.util.Random;

/**
 * Created by ujvaricsaba on 11/14/14.
 */
public class InitialData {
    private static final Long RANGE_OF_RANDOM = 9999L;
    public static void load() {
        createDesks();
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
}
