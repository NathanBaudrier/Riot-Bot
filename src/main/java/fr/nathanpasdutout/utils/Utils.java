package fr.nathanpasdutout.utils;

public class Utils {

    public static String capitalizeFirstLetter(String content) {
        return content.substring(0, 1).toUpperCase() + content.substring(1).toLowerCase();
    }
}
