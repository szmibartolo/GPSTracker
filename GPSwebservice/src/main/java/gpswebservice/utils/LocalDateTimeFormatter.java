package gpswebservice.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeFormatter {

    private static final String FORMAT = "yyyyMMdd'T'HHmmss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    public static String parse(LocalDateTime localDateTime) {
        return localDateTime.format(FORMATTER);
    }

    public static LocalDateTime parse(String intputString) {
        return LocalDateTime.parse(intputString, FORMATTER);
    }
}
