package com.smartstorm;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import org.yaml.snakeyaml.Yaml;

public class jsonMapper {
    public static JSONObject convertJson(JSONObject json, String filename) {
        String key;
        String key_to_map;
        JSONObject mappingJSON = YamlReaderToJSON.getJSONfromYAMLfile(filename);
        key_to_map = mappingJSON.getString("mapping");
        mappingJSON.remove("mapping");
        Iterator<?> keys = mappingJSON.keys();
        while(keys.hasNext())
        {
            key = (String) keys.next();
            JSONObject modifiedJson = mappingJSON.getJSONObject(key);
            String field_to_get = modifiedJson.getString(key_to_map);
            String value = getNestedValue(field_to_get, json);
            modifiedJson.put(key_to_map, value);
            mappingJSON.put(key,modifiedJson);
        }
        return mappingJSON;
    }

    public static String getNestedValue(String key, JSONObject json)
    {
        JSONObject inner = json;
        String[] keys = key.split("\\.");
        int i = 0;
        while(i < keys.length - 1)
        {
            inner = inner.getJSONObject(keys[i]);
            i++;
        }
        return String.valueOf(inner.get(keys[keys.length - 1]));
    }


}
