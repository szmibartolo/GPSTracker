package pl.wsb.szmibartolo.bartosz.gpstracker.utils;

import org.json.JSONException;
import org.json.JSONObject;


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
