package pl.wsb.szmibartolo.bartosz.gpstracker.utils;


import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;

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
