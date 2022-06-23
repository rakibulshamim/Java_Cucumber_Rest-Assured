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

public class createNewUserSteps {
//    public static Response response;
//    Properties properties = new Properties();
//    public static FileInputStream file;
//
//    static {
//        try {
//            file = new FileInputStream("./src/test/resources/config.properties");
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
    Properties properties = new Properties();
    FileInputStream file = new FileInputStream("./src/test/resources/config.properties");
    Response response;

    public createNewUserSteps() throws FileNotFoundException {
    }

    @Given("Post Call to user API for create new user")
    public void post_call_to_user_api_for_create_new_user() throws IOException {
        properties.load(file);
        RestAssured.baseURI = properties.getProperty("userBaseUrl");
        response =given()
                .contentType("application/json")
                .queryParam("access-token", properties.getProperty("access-token"))
                .body("{\"name\":\"Liton khan\"," +
                         " \"gender\":\"male\"," +
                         " \"email\":\"liton.khan123@gmail.com\"," +
                         " \"status\":\"active\"}")
                .when()
                .post("/users")
                .then().extract().response();
        System.out.println(response.asString());
    }

    @Then("User created successfully and {string} is validated")
    public void userCreatedSuccessfullyAndIsValidated(String responseMessage) {
        int responseCode = response.then().extract().statusCode();
        Assert.assertEquals(responseCode+"", responseMessage);
        System.out.println("Response Code: "+responseCode);
    }
}
