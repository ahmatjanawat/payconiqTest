package com.payconiq.stepDefinitions;


import com.payconiq.utilities.ConfigurationReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.Random;
import static io.restassured.RestAssured.given;

public class GetBookingByIdStepDef {
    Response response;
    String base_url = ConfigurationReader.get("base_url");

    @When("User send get  request with valid bookingId")
    public void user_send_get_request_with_valid_bookingId() {

        // step of sending  get request  to get booking id
        response = given().accept(ContentType.JSON).when()
                .get(base_url + "/booking");

        // step of getting random id
        ArrayList<Integer> ids = response.path("bookingid");
        int validId = ids.get(new Random().nextInt(ids.size()));

        // step of sending get request with valid id
        response = given().contentType(ContentType.JSON)
                .pathParam("id", validId)
                .when()
                .get(base_url + "/booking/" + "{id}");

    }

    @Then("Status code get booking by id should be {int}")
    public void status_code_get_booking_by_id_should_be(int int1) {
        // step of verification for status code
        Assert.assertEquals(response.statusCode(), int1);
    }

    @Then("User should be able to see all the booking information")
    public void user_should_be_able_to_see_all_the_booking_information() {

        //step of assertion for got   data
        Assert.assertTrue(response.body().asString().contains("firstname"));
        Assert.assertTrue(response.body().asString().contains("lastname"));
        Assert.assertTrue(response.body().asString().contains("totalprice"));
        Assert.assertTrue(response.body().asString().contains("depositpaid"));
        Assert.assertTrue(response.body().asString().contains("bookingdates"));
        Assert.assertTrue(response.body().asString().contains("checkin"));
        Assert.assertTrue(response.body().asString().contains("checkout"));
    }

    @When("User send get  request with invalid  bookingId")
    public void user_send_get_request_with_invalid_bookingId() {

        // step of sending get request with invalid id
        int inValidId = 99999999;
        response = given().contentType(ContentType.JSON)
                .pathParam("id", inValidId)
                .when()
                .get(base_url + "/booking/" + "{id}");
    }

    @Then("Status code should be {int}")
    public void status_code_should_be(int int1) {
        // step of verification for status code
        Assert.assertEquals(response.statusCode(), int1);
    }

    @Then("User should   be able to see {string} message")
    public void user_should_be_able_to_see_message(String string) {
        // step of assertion  for   "Not Found" message
        Assert.assertTrue(response.body().asString().contains(string));
    }

}
