package gpswebservice.utils;

import gpswebservice.model.Token;

import java.time.LocalDateTime;

public class TokenGenerator {

    public static Token generateNewToken(String login) {
        LocalDateTime now = LocalDateTime.now();
        return new Token(login + LocalDateTimeFormatter.parse(now));
    }
}
