package com.payconiq.stepDefinitions;


import com.payconiq.utilities.ConfigurationReader;
import com.payconiq.utilities.CreateToken;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import static io.restassured.RestAssured.given;

public class PartialUpdateBookingStepDef {

    Response response;
    Response responsePatch;
    String base_url = ConfigurationReader.get("base_url");

    @When("User send patch request with end point {string}")
    public void user_send_patch_request_with_end_point(String string) {

        // step of sending  get request  to get booking id
        response = given().accept(ContentType.JSON).when()
                .get(base_url + string);

        // step of getting random id
        ArrayList<Integer> ids = response.path("bookingid");
        int validId = ids.get(new Random().nextInt(ids.size()));

        // step of sending get request with valid id
        response = given().contentType(ContentType.JSON)
                .pathParam("id", validId)
                .when()
                .get(base_url + "/booking/" + "/{id}");

        //step of creating one map for patch  request json body
        Map<String, Object> putRequestMap = new HashMap<>();
        putRequestMap.put("firstname", "Sara");
        putRequestMap.put("lastname", "Smith");

        //step of  sending   patch request
        responsePatch = given()
                .accept("application/json")
                .and()
                .contentType("application/json; charset=utf-8")
                .header("Cookie", "token=" + CreateToken.createToken())
                .pathParam("id", validId)
                .and()
                .body(putRequestMap)
                .when()
                .patch(base_url + "/booking" + "/{id}");
    }

    @Then("Status code of partial update booking should be {int}")
    public void status_code_of_partial_update_booking_should_be(int int1) {

        //step of verification for status code
        Assert.assertEquals(responsePatch.statusCode(), 200);
    }

    @Then("User should be able to see all of updated information of the book")
    public void user_should_be_able_to_see_all_of_updated_information_of_the_book() {

        //step of assertion for patched   data
        JsonPath jsonPath = responsePatch.jsonPath();
        Assert.assertEquals("Sara", jsonPath.get("firstname"));
        Assert.assertEquals("Smith", jsonPath.get("lastname"));
    }

}
