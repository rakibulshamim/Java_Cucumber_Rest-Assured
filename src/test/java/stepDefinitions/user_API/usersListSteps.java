package stepDefinitions.user_API;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import static io.restassured.RestAssured.given;

public class usersListSteps {
    Properties properties = new Properties();
    FileInputStream file = new FileInputStream("./src/test/resources/config.properties");
    Response response;

    public usersListSteps() throws FileNotFoundException {
    }

    @Given("Get Call to user API for users details")
    public void get_call_to_user_api_for_users_details() throws IOException {
        properties.load(file);
        RestAssured.baseURI = properties.getProperty("userBaseUrl");
        response = given()
                .contentType("application/json")
                .queryParam("access-token", properties.getProperty("access-token"))
                .when()
                .get("/users");
        System.out.println(response.asString());
    }

    @Then("Http Response Code {string} is validated")
    public void http_response_code_is_validated(String responseMessage) {
        int responseCode = response.then().extract().statusCode();
        Assert.assertEquals(responseCode+"", responseMessage);
        System.out.println("Response Code: "+responseCode);
    }
}
