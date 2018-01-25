package com.smartstorm;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

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
}
