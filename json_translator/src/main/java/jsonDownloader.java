import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class jsonDownloader {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        URLConnection connection = new URL(url).openConnection();
        connection.setRequestProperty("Accept", "application/json");
        InputStream is = connection.getInputStream();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;
            String jsonText =  in.readLine();
            return new JSONObject(jsonText);
        } finally {
            is.close();
        }
    }

    public static void main(String[] args) throws IOException, JSONException {
        JSONObject json = readJsonFromUrl("https://danepubliczne.imgw.pl/api/data/synop/station/gdansk");
        System.out.println(json.toString());
        System.out.println(json.get("godzina_pomiaru"));
    }
}