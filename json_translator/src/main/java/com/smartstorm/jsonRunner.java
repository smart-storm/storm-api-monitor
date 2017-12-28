package com.smartstorm;

import org.json.JSONObject;

import java.io.IOException;

import static com.smartstorm.jsonDownloader.*;

public class jsonRunner {
    public static void main(String[] args) throws IOException {
        JSONObject jsonObject =
                readJsonFromUrl("https://danepubliczne.imgw.pl/api/data/synop/station/gdansk");
        jsonObject = jsonMapper.convertToJson(jsonObject, "json_translator/temp_hum.yml");
        jsonSender sender = new jsonSender();
        sender.sendJsons(jsonObject);
    }
}
