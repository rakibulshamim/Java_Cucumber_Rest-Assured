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

public class deleteUserSteps {
    Properties properties = new Properties();
    FileInputStream file = new FileInputStream("./src/test/resources/config.properties");
    Response response;

    public deleteUserSteps() throws FileNotFoundException {
    }

    @Given("Delete Call to user API for delete user info")
    public void delete_call_to_user_api_for_delete_user_info() throws IOException {
        properties.load(file);
        RestAssured.baseURI = properties.getProperty("userBaseUrl");
        response =given()
                .contentType("application/json")
                .queryParam("access-token", properties.getProperty("access-token"))
                .when()
                .delete("/users/4194")
                .then().extract().response();
        System.out.println(response.asString());
    }

    @Then("User info delete successfully and {string} is validated")
    public void user_info_delete_successfully_and_is_validated(String responseMessage) {
        int responseCode = response.then().extract().statusCode();
        Assert.assertEquals(responseCode+"", responseMessage);
        System.out.println("Response Code: "+responseCode);
    }
}
