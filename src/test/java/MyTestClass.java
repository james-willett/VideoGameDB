import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class MyTestClass {


    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/videogames";

    }

    @Test
    public void getAllVideoGames() {
        given().
        when().
                get("/").
        then().
                statusCode(200);
    }

    @Test
    public void printOutResponseBody() {
        String responseBody = get("/").asString();
        System.out.println(responseBody);
    }

}
