package com.payconiq.stepDefinitions;


import com.payconiq.utilities.ConfigurationReader;
import com.payconiq.utilities.CreateToken;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.Random;
import static io.restassured.RestAssured.given;


public class DeleteBookingStepDef {
    Response response;
    Response responseDelete;
    int validId;
    String base_url = ConfigurationReader.get("base_url");

    @When("User send delete request with end point")
    public void user_send_delete_request_with_end_point() {

        // step of sending  get request  to get booking id
        response = given().accept(ContentType.JSON).when()
                .get(base_url + "/booking");

        // step of getting random id
        ArrayList<Integer> ids = response.path("bookingid");
        validId = ids.get(new Random().nextInt(ids.size()));

        //step of sending delete request
        responseDelete = given()
                .accept("application/json")
                .header("Cookie", "token=" + CreateToken.createToken())
                .and().pathParam("id", validId)
                .when()
                .delete(base_url + "/booking" + "/{id}");
    }


    @Then("status code of delete booking  should be {int}")
    public void status_code_of_delete_booking_should_be(int int1) {

        // step of assertion for status code
        Assert.assertEquals(responseDelete.statusCode(), int1);
    }

    @Then("User should be able to see {string} message")
    public void user_should_be_able_to_see_message(String string) {

        // step of assertion for "created"  message
        Assert.assertTrue(responseDelete.body().asString().contains(string));
    }


}
