package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final DateFormat DATE_ONLY_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static final DateFormat LOCAL_DATE_FORMAT = new SimpleDateFormat();
    public static final DateFormat LOCAL_DATE_ONLY_FORMAT = DateFormat.getDateInstance(DateFormat.DATE_FIELD);


    public static String format(Date date) {
        if( date == null ) {
            return "-";
        } else {
            return DATE_FORMAT.format(date);
        }
    }

    public static String formatByLocale(Date date) {
        if( date == null ) {
            return "-";
        } else {
            return LOCAL_DATE_FORMAT.format(date);
        }
    }

    public static Date parse(String date) {
        if( date != null) {
            try {
                return DATE_FORMAT.parse(date);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } else {
            return null;
        }
    }

    public static Date parse(String date, String pattern) {
        if( date != null ) {
            try {
                return new SimpleDateFormat(pattern).parse(date);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } else {
            return null;
        }
    }

    public static String formatDateOnly(Date date) {
        if( date == null ) {
            return "-";
        } else {
            return DATE_ONLY_FORMAT.format(date);
        }
    }

    public static String formatDateOnlyByLocale(Date date) {
        if( date == null ) {
            return "-";
        } else {
            return LOCAL_DATE_ONLY_FORMAT.format(date);
        }
    }

    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }
}
