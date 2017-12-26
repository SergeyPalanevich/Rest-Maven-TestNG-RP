package com.epam.framework.gist.api.tests;

import com.epam.framework.gist.api.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class RestTemplateTest {

    private static final String URI = "http://jsonplaceholder.typicode.com/users";
    private static final int STATUS_CODE = 200;
    private static final String KEY_OF_HEADER = "content-type";
    private static final String VALUE_OF_HEADER = "application/json";
    private static final int LENGTH_ARRAY = 10;


    @Test()
    public void checkStatusCode(){
        RestTemplate rt = new RestTemplate();
        ResponseEntity<User[]> response = rt.getForEntity(URI, User[].class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        int statusCode = response.getStatusCodeValue();
        assertEquals(statusCode, STATUS_CODE);
    }

    @Test()
    public void checkHeader(){
        RestTemplate rt = new RestTemplate();
        ResponseEntity<User[]> response = rt.getForEntity(URI, User[].class);
        List<String> headers = response.getHeaders().get(KEY_OF_HEADER);
        assertTrue(headers.get(0).contains(VALUE_OF_HEADER));
    }

    @Test()
    public void checkBody(){
        RestTemplate rt = new RestTemplate();
        ResponseEntity<User[]> response = rt.getForEntity(URI, User[].class);
        assertEquals(response.getBody().length, LENGTH_ARRAY);
    }
}
