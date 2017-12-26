package com.epam.framework.gist.api.tests;

import com.epam.framework.gist.api.models.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class RestAssuredTest {
    private static final String URI = "http://jsonplaceholder.typicode.com";
    private static final int STATUS_CODE = 200;
    private static final String VALUE_OF_HEADER = "application/json";
    private static final int LENGTH_ARRAY = 10;
    private static final String KEY_OF_HEADER = "content-type";
    private static final String LOCATION = "/users";

    @BeforeClass()
    public void setUp() {
        RestAssured.baseURI = URI;
    }

    @Test()
    public void checkStatusCode(){
        Response rp = given().get(LOCATION).andReturn();
        int statusCode = rp.getStatusCode();
        assertEquals(statusCode, STATUS_CODE);
    }

    @Test()
    public void checkHeader(){
        Response rp = given().get(LOCATION).andReturn();
        String valueOfHeader = rp.getHeader(KEY_OF_HEADER);
        assertTrue(valueOfHeader.contains(VALUE_OF_HEADER));
    }

    @Test()
    public void checkBody(){
        Response rp = given().get(LOCATION).andReturn();
        User[] obj = rp.as(User[].class);
        assertEquals(obj.length, LENGTH_ARRAY);
    }
}
