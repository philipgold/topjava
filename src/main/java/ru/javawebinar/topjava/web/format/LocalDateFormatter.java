package ru.javawebinar.topjava.web.format;

import org.springframework.format.Formatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class LocalDateFormatter implements Formatter<LocalDate> {
    private String pattern;

    public LocalDateFormatter(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public LocalDate parse(String formatted, Locale locale) {
        if (formatted.length() == 0) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(this.pattern).withLocale(locale);
        return LocalDate.parse(formatted, formatter);
    }

    @Override
    public String print(LocalDate localDate, Locale locale) {
        if (localDate == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(this.pattern).withLocale(locale);
        return localDate.format(formatter);
    }
}