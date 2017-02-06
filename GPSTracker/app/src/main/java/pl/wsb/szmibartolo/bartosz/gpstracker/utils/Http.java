package pl.wsb.szmibartolo.bartosz.gpstracker.utils;

import android.util.Log;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.zip.GZIPInputStream;

/**
 * Created by Grzegorz on 15-01-2017.
 */

public class Http {

    public void login(String user, String password, HttpResultInterface listener) {
        StringBuilder stringBuilder = new StringBuilder(Config.URL);
        stringBuilder.append("login");

        HttpUriRequest httpUriRequest = new HttpGet(stringBuilder.toString());
        httpUriRequest.addHeader("Accept-Encoding", "gzip");
        httpUriRequest.addHeader("x-auth-login", user);
        httpUriRequest.addHeader("x-auth-pass", password);

        sendRequest(httpUriRequest, listener);
    }

    public void sendData(String token, double latitude, double longitude, String type, HttpResultInterface listener) {
        StringBuilder stringBuilder = new StringBuilder(Config.URL);
        stringBuilder.append("work?");
        stringBuilder.append("date=");
        stringBuilder.append(URLEncoder.encode(Calendar.getInstance().getTime().toString()));
        stringBuilder.append("&lat=");
        stringBuilder.append(latitude);
        stringBuilder.append("&long=");
        stringBuilder.append(longitude);
        if (type != null) {
            stringBuilder.append("&type=");
            stringBuilder.append(type);
        }

        HttpUriRequest httpUriRequest = new HttpGet(stringBuilder.toString());
        httpUriRequest.addHeader("Accept-Encoding", "gzip");
        httpUriRequest.addHeader("x-auth-token", token);

        sendRequest(httpUriRequest, listener);
    }

    public void sendRequest(final HttpUriRequest request, final HttpResultInterface listener) {

        //////////////TODO: ///////////////
        //Na razie jedziemy z mock'a bo nie ma webserwisu
        if (Config.USE_MOCK == true) {

            Log.d("test", request.getURI().toString());

            JsonParser parser = new JsonParser();
            String token = null;
            try {
                token = parser.parse(Config.jsonResponse);

                if (listener != null) {
                    listener.onResult(200, token);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            ////////////////////////////
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int code = 0;
                    try {
                        HttpClient httpClient = new DefaultHttpClient();
                        HttpResponse httpResponse = httpClient.execute(request);

                        code = httpResponse.getStatusLine().getStatusCode();

                        if (code == 200 || code == 202) {
                            InputStream inputStream = httpResponse.getEntity().getContent();
                            Header contentEncoding = httpResponse.getFirstHeader("Content-Encoding");
                            Header contentLength = httpResponse.getFirstHeader("Content-Length");
                            if (contentEncoding != null && contentEncoding.getValue().equalsIgnoreCase("gzip")) {
                                inputStream = new GZIPInputStream(inputStream);
                            }

                            byte[] bytes = new byte[Integer.parseInt(contentLength.getValue())];
                            inputStream.read(bytes);
                            inputStream.close();

                            JsonParser parser = new JsonParser();
                            String token = parser.parse(bytes);

                            if (listener != null) {
                                listener.onResult(code, token);
                            }
                        } else {

                            if (listener != null) {
                                listener.onResult(code, null);
                            }
                        }

                    } catch (Exception e) {

                        if (listener != null) {
                            listener.onResult(code, null);
                        }
                    }
                }
            }).start();
        }
    }
}
