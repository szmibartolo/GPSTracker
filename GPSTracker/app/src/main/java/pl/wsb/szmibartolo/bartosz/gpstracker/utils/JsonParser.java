package pl.wsb.szmibartolo.bartosz.gpstracker.utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Grzegorz on 15-01-2017.
 */

public class JsonParser {

    public String parse(String jsonString) throws  JSONException{
        JSONObject json = new JSONObject(jsonString);
        String token = json.getString("token");
        return token;
    }

    public String parse(byte[] bytes) throws JSONException{
        return parse(new String(bytes));
    }
}
