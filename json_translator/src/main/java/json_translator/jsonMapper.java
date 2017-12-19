package json_translator;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import org.yaml.snakeyaml.Yaml;

public class jsonMapper {

    jsonMapper() {

    }

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

    public static String convertToJson(JSONObject json, String yamlString) {
        Yaml yaml = new Yaml();

        String yamltext = readFile(yamlString);
        //mapowanie
        Map<String, Object> result = (Map<String, Object>) yaml.load(yamltext);

        JSONObject jsonObject = new JSONObject(result);
        //jsonObject.put("temperaturameasure_value", json.get("temperatura"));
        return jsonObject.toString();
    }

}
