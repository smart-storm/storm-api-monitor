package com.smartstorm;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import com.smartstorm.jsonMapper;

import static junit.framework.TestCase.assertTrue;

@RunWith(JUnitPlatform.class)
public class jsonMapperTest {
    @Test
    public void getNestedValueTest()
    {
        /* http://samples.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=b6907d289e10d714a6e88b30761fae22 */
        String sample = "{\"coord\":{\"lon\":139.01,\"lat\":35.02},\"weather\":[{\"id\":800,\"main\":\"Clear\","+
                        "\"description\":\"clear sky\",\"icon\":\"01n\"}],\"base\":\"stations\",\"main\" " +
                        ":{\"temp\":285.514,\"pressure\":1013.75,\"humidity\":100,\"temp_min\":285.514, " +
                        "\"temp_max\":285.514,\"sea_level\":1023.22,\"grnd_level\":1013.75},\"wind\":{\"speed\"" +
                        ":5.52,\"deg\":311},\"clouds\":{\"all\":0},\"dt\":1485792967,\"sys\":{\"message\":0.0025," +
                        "\"country\":\"JP\",\"sunrise\":1485726240,\"sunset\":1485763863},\"id\":1907296,\"name\"" +
                        ":\"Tawarano\",\"cod\":200}";
        JSONObject sampleJson = new JSONObject(sample);
        String result = com.smartstorm.jsonMapper.getNestedValue("main.temp", sampleJson);
        /* The result should be main.temp value which is set to 285.514 above */
        assertTrue(result.equals("285.514"));
    }

}
