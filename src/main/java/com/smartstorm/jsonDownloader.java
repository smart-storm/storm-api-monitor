package com.smartstorm;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class jsonDownloader {

    /**
     * @param url
     * @return
     * @throws IOException
     * @throws JSONException
     */
    //dodanie autoryzacji
    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        URLConnection connection = new URL(url).openConnection();
        connection.setRequestProperty("Accept", "application/json");
        InputStream is = connection.getInputStream();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String jsonText = in.readLine();
            return new JSONObject(jsonText);
        } finally {
            is.close();
        }
    }

    public static JSONObject readConfigAndReturnData(JSONObject config) throws IOException {
        JSONObject auth;
        String jsonText;
        String url = config.getString("url");
        try {
             auth = config.getJSONObject("authentication");
        }
        catch (JSONException ex)
        {
            auth = null;
        }
        String queryString = getQueryString(auth);
        URLConnection connection = new URL(url + queryString).openConnection();
        connection.setRequestProperty("Accept", "application/json");

        InputStream is = connection.getInputStream();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            jsonText = in.readLine();
        } finally {
            is.close();
        }
        return new JSONObject(jsonText);
    }

    public static String getQueryString(JSONObject auth)
    {
        if (auth == null)
            return "";
        String key;
        Iterator<?> keys = auth.keys();
        StringBuilder sb = new StringBuilder();
        sb.append("?");
        while(keys.hasNext())
        {
            key = (String) keys.next();
            String value = String.valueOf(auth.get(key));
            if (sb.length() > 0) {
                sb.append("&");
            }
            try {
                sb.append((key != null ? URLEncoder.encode(key, "UTF-8") : ""));
                sb.append("=");
                sb.append(value != null ? URLEncoder.encode(value, "UTF-8") : "");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("This method requires UTF-8 encoding support", e);
            }
        }
        return sb.toString();
    }
}
