package stepDefinitions.weather_API;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;
import static io.restassured.RestAssured.given;

public class forecastSteps {
    Properties properties = new Properties();
    FileInputStream file = new FileInputStream("./src/test/resources/config.properties");
    Response response;

    public forecastSteps() throws FileNotFoundException {
    }

    @Given("Get Call to forecast API")
    public void get_call_to_forecast_api() throws IOException {
        properties.load(file);
        RestAssured.baseURI = properties.getProperty("baseUrl");
        response = given()
                .contentType("application/json")
                .queryParam("key", properties.getProperty("key"))
                .queryParam("q", properties.getProperty("q"))
                .queryParam("days", properties.getProperty("days"))
                .when()
                .get("/forecast.json");
        System.out.println(response.asString());
    }

    @Then("Response code {string} is validated")
    public void response_code_is_validated(String responseMessage) {
        int responseCode = response.then().extract().statusCode();
        Assert.assertEquals(responseCode+"", responseMessage);
        System.out.println("Response Code: "+responseCode);
    }

    @Given("Get Call to forecast API with alerts parameter")
    public void get_call_to_forecast_API_with_alerts_parameter() throws IOException {
        properties.load(file);
        RestAssured.baseURI = properties.getProperty("baseUrl");
        response = given()
                .contentType("application/json")
                .queryParam("key", properties.getProperty("key"))
                .queryParam("q", properties.getProperty("q"))
                .queryParam("days", properties.getProperty("days"))
                .queryParam("alerts", properties.getProperty("alerts"))
                .when()
                .get("/forecast.json");
        System.out.println(response.asString());
    }

    @Then("weather alerts are validated")
    public void weather_alerts_are_validated() {
        JsonPath resObj = response.jsonPath();
        String text = resObj.get("alerts").toString();
        Assert.assertEquals(text,"{alert=[]}");
        System.out.println(text);
    }

    @Then("forecast {string} is validated")
    public void forecast_is_validated(String date) {
        JsonPath resObj = response.jsonPath();
        String text = resObj.get("forecast.forecastday.date[0]").toString();
        Assert.assertEquals(text, date);
        System.out.println(text);
    }

    @Then("forecast hour is validated")
    public void forecast_hour_is_validated() {
        JsonPath resObj = response.jsonPath();
        String text = resObj.get("forecast.forecastday.hour[0].time[0]").toString();
        Assert.assertEquals(text, "2022-06-23 00:00");
        System.out.println(text);
    }

    @Then("sunset time is validated")
    public void sunset_time_is_validated() {
        JsonPath resObj = response.jsonPath();
        String text = resObj.get("forecast.forecastday.astro.sunset[0]").toString();
        Assert.assertEquals(text, "06:49 PM");
        System.out.println(text);
    }
}
