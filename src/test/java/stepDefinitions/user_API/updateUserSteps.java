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

public class updateUserSteps {
    Properties properties = new Properties();
    FileInputStream file = new FileInputStream("./src/test/resources/config.properties");
    Response response;

    public updateUserSteps() throws FileNotFoundException {
    }

    @Given("Put Call to user API for update user info")
    public void put_call_to_user_api_for_update_user_info() throws IOException {
        properties.load(file);
        RestAssured.baseURI = properties.getProperty("userBaseUrl");
        response =given()
                .contentType("application/json")
                .queryParam("access-token", properties.getProperty("access-token"))
                .body("{\"name\":\"Titu khan\"," +
                        " \"gender\":\"male\"," +
                        " \"email\":\"titu.khan123@gmail.com\"," +
                        " \"status\":\"active\"}")
                .when()
                .put("/users/3396")
                .then().extract().response();
        System.out.println(response.asString());
    }

    @Given("Patch Call to user API for update user info")
    public void patch_call_to_user_api_for_update_user_info() throws IOException {
        properties.load(file);
        RestAssured.baseURI = properties.getProperty("userBaseUrl");
        response =given()
                .contentType("application/json")
                .queryParam("access-token", properties.getProperty("access-token"))
                .body("{\"name\":\"Sakib Hasan\"}")
                .when()
                .put("/users/3396")
                .then().extract().response();
        System.out.println(response.asString());
    }

    @Then("User info update successfully and {string} is validated")
    public void user_info_update_successfully_and_is_validated(String responseMessage) {
        int responseCode = response.then().extract().statusCode();
        Assert.assertEquals(responseCode+"", responseMessage);
        System.out.println("Response Code: "+responseCode);
    }

}
