package com.smartstorm;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import org.yaml.snakeyaml.Yaml;

public class jsonMapper {


    //dodanie uniwersalnosci
    public static String readFile(String filename) {
        String content = null;
        File file = new File(filename); //for ex foo.txt
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            content = new String(chars);
            reader.close();
        } catch (IOException e) {
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    Logger.getLogger(jsonMapper.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return content;
    }

    public static JSONObject convertToJson(JSONObject json, String yamlString) {
        Yaml yaml = new Yaml();

        String yamltext = readFile(yamlString);
        //mapowanie
        Map<String, Object> result = (Map<String, Object>) yaml.load(yamltext);

        String key;
        JSONObject jsonObject = new JSONObject(result);
        Iterator<?> keys = jsonObject.keys();
        while(keys.hasNext())
        {
            key = (String) keys.next();
            JSONObject modifiedJson = jsonObject.getJSONObject(key);
            String field_to_get = modifiedJson.getString("measure_value");
            String value = json.getString(field_to_get);
            modifiedJson.put("measure_value", value);
            jsonObject.put(key,modifiedJson);
        }
        return jsonObject;
    }

}
