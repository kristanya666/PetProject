package tests;

import data.APIDataHelper;
import data.Data;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpeciesAPITest {
    RequestSpecification requestSpec = APIDataHelper.requestSpec(Data.getBaseUrl());
    String path = Data.getBaseUrl() + Data.getSpecies();
    int firstID = 1;
    int maxID = 37;
    int invalidCountOfSpecies = maxID + 1;

    @Test
    public void requestSpeciesOk() {
        given()
                .spec(requestSpec)
                .when()
                .get(path)
                .then()
                .statusCode(200);
    }

    @Test
    public void requestSpeciesWithValidIdIsOk() {
        given()
                .spec(requestSpec)
                .when()
                .get(path + maxID)
                .then()
                .statusCode(200);
    }

    @Test
    public void haveAccessToSpeciesByID() {
        given()
                .spec(requestSpec)
                .when()
                .get(path + firstID)
                .then()
                .statusCode(200)
                .assertThat()
                .body("name", equalTo("Human"));
    }

    @Test
    public void speciesMatchesJSONSchema() {

        given()
                .spec(requestSpec)
                .when()
                .get(path + firstID)
                .then()
                .body(matchesJsonSchemaInClasspath("species.schema.json"));
    }

    @Test
    public void whenValidateResponseTimeInSeconds_thenSuccess() {
        int maxSeconds = 5;

        Response response = RestAssured.get(path);
        long timeInS = response.timeIn(TimeUnit.SECONDS);

        assertTrue(timeInS <= maxSeconds);
    }

    @Test
    public void postRequestShouldError() {
        given()
                .spec(requestSpec)
                .body(Data.getRequestBody())
                .when()
                .post(path)
                .then()
                .statusCode(405);
    }

    @Test
    public void invalidCountOfSpeciesShouldError() {
        given()
                .spec(requestSpec)
                .when()
                .get(path + invalidCountOfSpecies)
                .then()
                .statusCode(404);
    }
}
