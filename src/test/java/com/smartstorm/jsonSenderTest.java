package com.smartstorm;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHttpResponse;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class jsonSenderTest {
    @Mock
    HttpClient httpClient;
    @Mock
    HttpPost httpPost;
    @Mock
    BasicHttpResponse response;
    @Mock
    BasicHttpEntity entity;

    @Test
    void BasicTest() throws IOException {
        InputStream is = new ByteArrayInputStream("FAKE OK".getBytes());
        when(httpClient.execute(httpPost)).thenReturn(response);
        when(response.getEntity()).thenReturn(entity);
        when(entity.getContent()).thenReturn(is);

        jsonSender js = new jsonSender(httpClient, httpPost);
        JSONObject json = new JSONObject();
        json.put("desc","LOL");
        JSONObject json_container = new JSONObject();
        json_container.put("desc",json);
        js.sendJsons(json_container);
    }

    @Test
    void BasicConstructor()
    {
        JSONObject json = new JSONObject();
        json.put("url", "http://example.org");
        jsonSender js = new jsonSender(json);
        assertNotNull(js);
    }
}