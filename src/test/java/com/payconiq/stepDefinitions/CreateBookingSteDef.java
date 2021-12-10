package com.payconiq.stepDefinitions;


import com.payconiq.utilities.ConfigurationReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;


public class CreateBookingSteDef {
    Response response;
    String base_url = ConfigurationReader.get("base_url");

    @When("User send post request with end point {string}")
    public void user_send_post_request_with_end_point(String string) {

        //step of creating  a map to keep request json body information
        Map<String, Object> requestMap = new HashMap<>();
        Map<String, Object> date = new HashMap<>();

        //step of adding value that we want to post
        date.put("checkin", "2021-01-01");
        date.put("checkout", "2021-01-10");
        requestMap.put("firstname", "Adem");
        requestMap.put("lastname", "Noah");
        requestMap.put("totalprice", 120);
        requestMap.put("depositpaid", true);
        requestMap.put("bookingdates", date);
        requestMap.put("additionalneeds", "lunch");

        // step of sending post request
        response = given().log().all()
                .accept("application/json")
                .and()
                .contentType("application/json; charset=utf-8")
                .and()
                .body(requestMap)
                .when()
                .post(base_url + string);


        // step of verification for   content type
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");
    }

    @Then("Status code of create booking  should be {int}")
    public void status_code_of_create_booking_should_be(int int1) {

        // step of  assertion for status code
        Assert.assertEquals(response.statusCode(), int1);
    }

    @Then("User should be able to see all of created information of the book")
    public void user_should_be_able_to_see_all_of_created_information_of_the_book() {

        // assertion for posted  data
        JsonPath jsonPath = response.jsonPath();
        String firstname = jsonPath.getString("booking.firstname");
        String lastname = jsonPath.getString("booking.lastname");
        int totalprice = jsonPath.getInt("booking.totalprice");
        Boolean depositpaid = jsonPath.getBoolean("booking.depositpaid");
        String checkin = jsonPath.getString("booking.bookingdates.checkin");
        String checkout = jsonPath.getString("booking.bookingdates.checkout");
        String additionalneeds = jsonPath.getString("booking.additionalneeds");

        Assert.assertEquals(firstname, "Adem");
        Assert.assertEquals(lastname, "Noah");
        Assert.assertEquals(totalprice, 120);
        Assert.assertEquals(depositpaid, true);
        Assert.assertEquals(checkin, "2021-01-01");
        Assert.assertEquals(checkout, "2021-01-10");
        Assert.assertEquals(additionalneeds, "lunch");


    }


}
