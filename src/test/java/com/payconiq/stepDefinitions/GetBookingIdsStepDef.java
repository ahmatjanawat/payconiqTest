package com.payconiq.stepDefinitions;


import com.payconiq.utilities.ConfigurationReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import java.util.List;

public class GetBookingIdsStepDef {

    Response response;
    String base_url = ConfigurationReader.get("base_url");

    @When("User send get request with end point {string}")
    public void user_send_get_request_with_end_point(String string) {

        //step of sending  get   request with end pint
        response = RestAssured.given()
                .accept(ContentType.JSON).when()
                .get(base_url + string);
    }

    @Then("Status code get booking ids should be {int}")
    public void status_code_get_booking_ids_should_be(int int1) {

        // step of verification for status code
        Assert.assertEquals(int1, response.statusCode());
    }

    @Then("Each booking id should be unique")
    public void each_booking_id_should_be_unique() {

        // step of verification of Array of objects that contain unique booking IDs
        int memberLength = 0;
        JsonPath jsonPath = response.jsonPath();
        List<Integer> bookIdLists = jsonPath.getList("bookingid");
        for (int i = 0; i < bookIdLists.size(); i++) {
            for (int j = 0; j < bookIdLists.size(); j++) {
                if (bookIdLists.get(j) == bookIdLists.get(i)) {
                    memberLength++;
                }
            }
            Assert.assertEquals(1, memberLength);
            memberLength = 0;
        }

    }


}
