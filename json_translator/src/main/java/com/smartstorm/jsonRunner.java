package com.smartstorm;

import org.json.JSONObject;

import java.io.IOException;

import static com.smartstorm.jsonDownloader.*;

public class jsonRunner {
    public static void main(String[] args) throws IOException {
        //zmienic argumenty na czytanie z pliku konfiguracyjnego w src/main/resources
        if (args.length < 2) {
            System.out.println("Required arguments: url_to_connect config_yaml_full_path");
            System.exit(0);
        }
        JSONObject jsonObject =
                readJsonFromUrl(args[0]);
        jsonObject = jsonMapper.convertToJson(jsonObject, args[1]);
        jsonSender sender = new jsonSender();
        sender.sendJsons(jsonObject);
    }
}
