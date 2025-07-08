package fr.nathanpasdutout.utils;

import java.time.OffsetDateTime;

public class Time {

    private OffsetDateTime date;

    public Time(String date) {
        this.date = OffsetDateTime.parse(date);
    }

    public String getTextFormat() {
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
