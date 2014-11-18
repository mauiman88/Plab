import play.Application;
import play.GlobalSettings;

/**
 * Created by ujvaricsaba on 11/14/14.
 */
public class Global extends GlobalSettings {

    @Override
    public void onStart(Application app) {
        InitialData.load();
    }
}
