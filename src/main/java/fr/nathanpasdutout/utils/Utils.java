package fr.nathanpasdutout.utils;

import java.time.OffsetDateTime;

public class Utils {

    public static String capitalizeFirstLetter(String content) {
        return content.substring(0, 1).toUpperCase() + content.substring(1).toLowerCase();
    }

    public static String getDateFormat(OffsetDateTime date) {
        if(date == null) return "N/A";

        return Utils.capitalizeFirstLetter(date.getMonth().name())
                + " "
                + date.getDayOfMonth()
                + ", at "
                + date.getHour()
                + ":"
                + date.getMinute()
                + ":"
                + date.getSecond();
    }
}
