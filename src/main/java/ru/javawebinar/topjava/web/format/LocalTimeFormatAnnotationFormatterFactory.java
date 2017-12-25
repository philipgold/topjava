package ru.javawebinar.topjava.web.format;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LocalTimeFormatAnnotationFormatterFactory implements AnnotationFormatterFactory<LocalTimeFormat> {
    @Override
    public Set<Class<?>> getFieldTypes() {
        return new HashSet<>(Arrays.asList(LocalTime.class));
    }

    @Override
    public Printer<?> getPrinter(LocalTimeFormat annotation, Class<?> fieldType) {
        return getLocalTimeFormatter(annotation, fieldType);
    }

    @Override
    public Parser<?> getParser(LocalTimeFormat annotation, Class<?> fieldType) {
        return getLocalTimeFormatter(annotation, fieldType);
    }

    private LocalTimeFormatter getLocalTimeFormatter (LocalTimeFormat annotation, Class<?> fieldType) {
        return new LocalTimeFormatter(annotation.pattern());
    }
}