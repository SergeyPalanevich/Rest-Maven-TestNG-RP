package com.epam.framework.gist.api.tests;

import com.epam.framework.gist.api.services.HTTPServices;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class GistApiTest {

    private static final int STATUS_CODE_CREATED = 201;
    private static final int STATUS_CODE_NO_CONTENT = 204;
    private static final int STATUS_CODE_OK = 200;
    private static final String PATH_TO_GIST_CREATE_JSON = "src/main/resources/json/gist_create.json";
    private static final String PATH_TO_GIST_UPDATE_JSON = "src/main/resources/json/gist_update.json";


    @Parameters({"uri", "token"})
    @Test(description = "This TC check Create a gist")
    public void checkPostRequest(String uri, String token) {
        int statusCode = new HTTPServices().post(uri + "?access_token=" + token, PATH_TO_GIST_CREATE_JSON);
        assertEquals(statusCode, STATUS_CODE_CREATED);
    }

    @Parameters({"uri", "token", "gistID"})
    @Test(description = "This TC check Star a gist")
    public void checkPutRequest(String uri, String token, String gistID) {
        int statusCode = new HTTPServices().put(uri + "/" + gistID + "/star?access_token=" + token);
        assertEquals(statusCode, STATUS_CODE_NO_CONTENT);
    }

    @Parameters({"uri", "token", "gistID"})
    @Test(description = "This TC check Unstar a gist")
    public void checkDeleteRequest(String uri, String token, String gistID) {
        int statusCode = new HTTPServices().delete(uri + "/" + gistID + "/star?access_token=" + token);
        assertEquals(statusCode, STATUS_CODE_NO_CONTENT);
    }

    @Parameters({"uri", "token", "gistID"})
    @Test(description = "This TC check Edit a gist")
    public void checkEditRequest(String uri, String token, String gistID) {
        int statusCode = new HTTPServices().patch(uri + "/" + gistID + "?access_token=" + token, PATH_TO_GIST_UPDATE_JSON);
        assertEquals(statusCode, STATUS_CODE_OK);
    }
}