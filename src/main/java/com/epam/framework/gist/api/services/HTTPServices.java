package com.epam.framework.gist.api.services;

import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

import static com.epam.framework.core.utils.FileUtils.getGsonString;

public class HTTPServices {

    public int put(String url) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPut put = new HttpPut(url);
        CloseableHttpResponse response = null;
        int statusCode = 0;
        try {
            response = client.execute(put);
            statusCode = response.getStatusLine().getStatusCode();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (client != null) {
                    client.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return statusCode;
    }


    public int delete(String url) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpDelete delete = new HttpDelete(url);
        CloseableHttpResponse response = null;
        int statusCode = 0;
        try {
            response = client.execute(delete);
            statusCode = response.getStatusLine().getStatusCode();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (client != null) {
                    client.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return statusCode;
    }

    public int post(String url, String pathToJson) {
        String json = getGsonString(pathToJson);
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        StringEntity requestEntity = new StringEntity(
                json,
                ContentType.APPLICATION_JSON);
        post.setEntity(requestEntity);
        CloseableHttpResponse response = null;
        int statusCode = 0;
        try {
            response = client.execute(post);
            statusCode = response.getStatusLine().getStatusCode();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (client != null) {
                    client.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return statusCode;
    }

    public int patch(String url, String pathToJson) {
        String json = getGsonString(pathToJson);
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPatch patch = new HttpPatch(url);
        StringEntity requestEntity = new StringEntity(
                json,
                ContentType.APPLICATION_JSON);
        patch.setEntity(requestEntity);
        CloseableHttpResponse response = null;
        int statusCode = 0;
        try {
            response = client.execute(patch);
            statusCode = response.getStatusLine().getStatusCode();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (client != null) {
                    client.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return statusCode;
    }
}
