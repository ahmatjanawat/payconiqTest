package com.payconiq.utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class CreateToken {

    public static String createToken() {
        String username = ConfigurationReader.get("username");
        String password = ConfigurationReader.get("password");

        //create one map  to store username and data
        Map<String, Object> getTokenRequestBody = new HashMap<>();
        getTokenRequestBody.put("username", username);
        getTokenRequestBody.put("password", password);

        //sending post request to get accesses token
        Response getToken = given().contentType(ContentType.JSON)
                .body(getTokenRequestBody)
                .when()
                .post(ConfigurationReader.get("token_url"));
        return getToken.path("token");
    }
}
