package utils;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberUtils {

    public static final Locale HU_LOCALE = new Locale("hu", "HU");

    public static String formatHUF(Number value) {
        if( value != null ) {
            NumberFormat numberFormat = NumberFormat.getCurrencyInstance(HU_LOCALE);
            return numberFormat.format(value);
        } else {
            return "-";
        }
    }

    public static String format(Number value) {
        if( value != null ) {
            NumberFormat numberFormat = NumberFormat.getInstance(HU_LOCALE);
            return numberFormat.format(value);
        } else {
            return "-";
        }
    }
}
