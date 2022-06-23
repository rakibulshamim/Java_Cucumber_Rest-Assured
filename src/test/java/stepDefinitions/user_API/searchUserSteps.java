package stepDefinitions.user_API;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import static io.restassured.RestAssured.given;

public class searchUserSteps {
    Properties properties = new Properties();
    FileInputStream file = new FileInputStream("./src/test/resources/config.properties");
    Response response;

    public searchUserSteps() throws FileNotFoundException {
    }

    @Given("Get Call to user API with specific user id")
    public void get_call_to_user_api_with_specific_user_id() throws IOException {
        properties.load(file);
        RestAssured.baseURI = properties.getProperty("userBaseUrl");
        response = given()
                .contentType("application/json")
                .queryParam("access-token", properties.getProperty("access-token"))
                .when()
                .get("/users/3396")
                .then().extract().response();
        System.out.println(response.asString());
    }

    @Then("user id {string} is validated")
    public void user_id_is_validated(String id) {
        JsonPath resObj = response.jsonPath();
        String text = resObj.get("id").toString();
        Assert.assertEquals(text, id);
        System.out.println("user id: " + text);
    }

    @And("user name {string} is validated")
    public void user_name_is_validated(String name) {
        JsonPath resObj = response.jsonPath();
        String text = resObj.get("name").toString();
        Assert.assertEquals(text, name);
        System.out.println("user name: " + text);
    }
}
