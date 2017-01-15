package pl.wsb.szmibartolo.bartosz.gpstracker.utils;

/**
 * Created by Grzegorz on 15-01-2017.
 */

public class Config {

    public final static String URL = "http://webservice.url/";
    public final static boolean USE_MOCK = true;
    public static String jsonResponse = "{\"token\":\"1234567890\"}";
    public final static int CHECK_TIME = 10 * 1000;//5*60*1000; //Docelowo ma byc 5*60*1000 czyli 5min, dla testu ustawiam 10sek
}
