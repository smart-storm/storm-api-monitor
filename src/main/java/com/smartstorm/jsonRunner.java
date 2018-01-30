package com.smartstorm;

import org.json.JSONObject;

import java.io.IOException;

import static com.smartstorm.jsonDownloader.*;

public class jsonRunner {
    public static void main(String[] args) throws IOException {
        if (args.length < 3) {
            System.out.println("Required arguments: mapping_yaml download_yaml upload_yaml");
            System.exit(0);
        }
        JSONObject downloadConfig = YamlReaderToJSON.getJSONfromYAMLfile(args[1]);
        JSONObject dataReceived = readConfigAndReturnData(downloadConfig);
        JSONObject dataToSend = jsonMapper.convertJson(dataReceived, args[0]);
        JSONObject senderConfig = YamlReaderToJSON.getJSONfromYAMLfile(args[2]);
        jsonSender sender = new jsonSender(senderConfig);
        sender.sendJsons(dataToSend);
    }
}
