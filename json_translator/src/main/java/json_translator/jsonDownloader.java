package json_translator;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class jsonDownloader {

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

    public static void main(String[] args) throws IOException, JSONException {
        JSONObject json = readJsonFromUrl("https://danepubliczne.imgw.pl/api/data/synop/station/gdansk");
        //zmieniæ na œcie¿kê wzglêdn¹
        String jsontoSend = jsonMapper.convertToJson(json, "C:\\Users\\Tyroke\\Desktop\\Semestr 9\\isp\\isp-proj-3\\json_translator\\src\\main\\java\\json_translator\\temp_hum.yml");
        System.out.println(jsontoSend);

    }
}
