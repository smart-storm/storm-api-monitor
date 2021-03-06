package com.smartstorm;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Scanner;

public class jsonSender {
    private HttpClient httpclient;
    private HttpPost httppost;
    private JSONObject senderConfig;

    jsonSender(JSONObject senderConfig) {
        httpclient = HttpClients.createDefault();
        httppost = new HttpPost(senderConfig.getString("url"));
    }

    jsonSender(HttpClient httpClient, HttpPost httpPost)
    {
        this.httppost = httpPost;
        this.httpclient = httpClient;
    }

    public void sendJsons(JSONObject jsonWithMessages) throws IOException {
        Iterator<?> keys = jsonWithMessages.keys();
        String key;
        while (keys.hasNext()) {
            key = (String) keys.next();
            JSONObject singleJson = jsonWithMessages.getJSONObject(key);
            sendSingleJSON(singleJson, key);
        }
    }

    //dodanie autoryzacji

    private void sendSingleJSON(JSONObject toSend, String key) throws IOException {
        System.out.print("Sending " + key +": ");
        httppost.setEntity(new StringEntity(toSend.toString()));
        httppost.setHeader("Content-type","application/json");
        //Execute and get the response
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();
        InputStream instream = entity.getContent();
        try {
            Scanner scanner = new Scanner(instream);
            scanner.useDelimiter("\\A");
            String str = scanner.next();
            System.out.println(str);

        } finally {
            instream.close();
        }
        }
}
