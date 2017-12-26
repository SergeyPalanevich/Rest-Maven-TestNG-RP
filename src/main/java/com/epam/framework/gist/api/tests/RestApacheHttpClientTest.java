package com.epam.framework.gist.api.tests;

import com.epam.framework.gist.api.models.User;
import com.google.gson.Gson;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class RestApacheHttpClientTest {
    private static final String URI = "http://jsonplaceholder.typicode.com/users";
    private static final int STATUS_CODE = 200;
    private static final String VALUE_OF_HEADER = "application/json";
    private static final int LENGTH_ARRAY = 10;
    private static final String KEY_OF_HEADER = "content-type";

    @Test()
    public void checkStatusCode() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(URI);
        CloseableHttpResponse response = httpclient.execute(httpGet);
        int statusCode = response.getStatusLine().getStatusCode();
        assertEquals(statusCode, STATUS_CODE);
    }

    @Test()
    public void checkHeader() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(URI);
        CloseableHttpResponse response = httpclient.execute(httpGet);
        Header[] headers = response.getHeaders(KEY_OF_HEADER);
        assertTrue(headers[0].getValue().contains(VALUE_OF_HEADER));
    }

    @Test()
    public void checkBody() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(URI);
        CloseableHttpResponse response = httpclient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        InputStream content = entity.getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(content));
        Gson gson = new Gson();
        User[] obj = gson.fromJson(reader, User[].class);
        assertEquals(obj.length, LENGTH_ARRAY);
    }
}
