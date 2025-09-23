package fr.nathanpasdutout.utils;

import java.time.OffsetDateTime;

public class Utils {

    public static String capitalizeFirstLetter(String content) {
        return content.substring(0, 1).toUpperCase() + content.substring(1).toLowerCase();
    }

    public static String getDateFormat(String date) {
        OffsetDateTime dateTime = OffsetDateTime.parse(date);
        return Utils.capitalizeFirstLetter(dateTime.getMonth().name())
                + " "
                + dateTime.getDayOfMonth()
                + ", at "
                + dateTime.getHour()
                + ":"
                + dateTime.getMinute()
                + ":"
                + dateTime.getSecond();
    }
}
