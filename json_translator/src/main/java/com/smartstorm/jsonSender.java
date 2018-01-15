package com.smartstorm;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class jsonSender {
    private HttpClient httpclient;
    private HttpPost httppost;

    jsonSender() {
        httpclient = HttpClients.createDefault();
        httppost = new HttpPost("http://alfa.smartstorm.io/api/v1/measure");
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
            sendSingleJSON(singleJson);
        }
    }


    private void sendSingleJSON(JSONObject toSend) throws IOException {
        System.out.print("Sending " + toSend.getString("desc") +": ");
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
