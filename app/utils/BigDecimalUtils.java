package utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

@Deprecated
public class BigDecimalUtils {

    public static final Locale HU_LOCALE = new Locale("hu", "HU");

    public static String formatHUF(BigDecimal price) {
        if( price != null ) {
            NumberFormat numberFormat = NumberFormat.getCurrencyInstance(HU_LOCALE);
            return numberFormat.format(price);
        } else {
            return "-";
        }
    }

    public static String format(BigDecimal value) {
        if( value != null ) {
            NumberFormat numberFormat = NumberFormat.getInstance(HU_LOCALE);
            return numberFormat.format(value);
        } else {
            return "-";
        }
    }
}
