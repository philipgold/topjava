package ru.javawebinar.topjava.web.format;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalTimeFormatter implements Formatter<LocalTime> {
    private String pattern;

    public LocalTimeFormatter(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public LocalTime parse(String formatted, Locale locale) throws ParseException {
        if (formatted.length() == 0) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(this.pattern).withLocale(locale);
        return LocalTime.parse(formatted, formatter);
    }

    @Override
    public String print(LocalTime localTime, Locale locale) {
        if (localTime == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(this.pattern).withLocale(locale);
        return localTime.format(formatter);
    }
}