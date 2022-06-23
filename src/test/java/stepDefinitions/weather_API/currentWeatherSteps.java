package stepDefinitions.weather_API;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import static io.restassured.RestAssured.given;


public class currentWeatherSteps {
    Properties properties = new Properties();
    FileInputStream file = new FileInputStream("./src/test/resources/config.properties");
    Response response;

    public currentWeatherSteps() throws FileNotFoundException {
    }

    @Given("Get Call to current weather API")
    public void get_call_to_current_weather_API() throws IOException {
        properties.load(file);
        RestAssured.baseURI = properties.getProperty("baseUrl");
        response = given()
                .contentType("application/json")
                .queryParam("key", properties.getProperty("key"))
                .queryParam("q", properties.getProperty("q"))
                .when()
                .get("/current.json");
        System.out.println(response.asString());
    }

    @Given("Get Call to current weather API with language parameter")
    public void get_call_to_current_weather_API_with_language_parameter() throws IOException {
        properties.load(file);
        RestAssured.baseURI = properties.getProperty("baseUrl");
        response =given()
                .contentType("application/json")
                .queryParam("key", properties.getProperty("key"))
                .queryParam("q", properties.getProperty("q"))
                .queryParam("lang", properties.getProperty("lang"))
                .when()
                .get("/current.json");
        System.out.println(response.asString());
    }

    @Then("Response Code {string} is validated")
    public void response_code_is_validated(String responseMessage) {
        int responseCode = response.then().extract().statusCode();
        Assert.assertEquals(responseCode+"", responseMessage);
        System.out.println("Response Code: "+responseCode);
    }

    @Then("Current weather condition text is validated")
    public void current_weather_condition_text_is_validated() {
        JsonPath resObj = response.jsonPath();
        String text = resObj.get("current.condition.text").toString();
        Assert.assertEquals(text,"হাল্কা স্বল্পস্থায়ী বৃষ্টিপাত");
        System.out.println(text);
    }

    @Then("Country name {string} is validated")
    public void country_name_is_validated(String country) {
        JsonPath resObj = response.jsonPath();
        String text = resObj.get("location.country").toString();
        Assert.assertEquals(text, country);
        System.out.println(text);
    }

    @When("Current date and time are displayed")
    public void current_date_and_time_are_displayed() {
        JsonPath resObj = response.jsonPath();
        String lastUpdated = resObj.get("current.last_updated").toString();
        System.out.println(lastUpdated);
    }

    @Then("location name {string} is validated")
    public void location_name_is_validated(String name) {
        JsonPath resObj = response.jsonPath();
        String text = resObj.get("location.name").toString();
        Assert.assertEquals(text, name);
        System.out.println(text);
    }
}
