package ru.mycompany.shishkin;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Utils {

    public static String dateIntoTimestamp(String date, String format) {
        Calendar cal = GregorianCalendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            cal.setTime(sdf.parse(date));
            Timestamp tstamp = new Timestamp(cal.getTimeInMillis());
            System.out.println("DateIntoTimestamp:" + tstamp + " = " + cal.getTimeInMillis());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return String.valueOf(cal.getTimeInMillis());
    }

    public static String urlBuilder(String host, String dashboardName,String timeFrom, String timeTo, String width,
                                    String height) {
        return "" + host + "" + dashboardName + "" + "&from=" + timeFrom + "&to=" + timeTo + "&width=" + width
                + "&height=" + height + "";
    }
}
