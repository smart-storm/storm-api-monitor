package com.smartstorm;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class jsonSender {
    private HttpClient httpclient;
    private HttpPost httppost;

    jsonSender() {
        httpclient = HttpClients.createDefault();
        httppost = new HttpPost("http://alfa.smartstorm.io/api/update");
    }

    public void sendValue(String sensor_id, String val) throws IOException {
        // Request parameters and other properties.
        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
        params.add(new BasicNameValuePair("user-id","t178705@mvrht.net"));
        params.add(new BasicNameValuePair("sensor_id", sensor_id));
        params.add(new BasicNameValuePair("measure_value", val));
        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        //Execute and get the response.
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            InputStream instream = entity.getContent();
            try {
                String str = new Scanner(instream).useDelimiter("\\A").next();
                System.out.println(str);

            } finally {
                instream.close();
            }
        }

    }

    public static void main(String[] args) throws IOException {
        jsonSender js = new jsonSender();
        js.sendValue("5a37ab4af7806676ddadd0dd", "10");

    }




}
