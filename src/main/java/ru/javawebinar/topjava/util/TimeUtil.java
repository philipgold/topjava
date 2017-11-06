package ru.javawebinar.topjava.util;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * GKislin
 * 07.01.2015.
 */
public class TimeUtil {
    public static boolean isBetween(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
    }

    /**
     * Using by functions.tld
     *
     * @see <a href="https://stackoverflow.com/questions/35606551/jstl-localdatetime-format">JSTL LocalDateTime format
    </a>
     */
    public static String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
        if (localDateTime != null)
            return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
        else
            return "";
    }
}
