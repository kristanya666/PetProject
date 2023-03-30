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

import static org.junit.jupiter.api.Assertions.*;

public class FilmsAPITest {
    RequestSpecification requestSpec = APIDataHelper.requestSpec(Data.getBaseUrl());
    String path = Data.getBaseUrl() + Data.getFilms();

    int firstID = 1;
    int maxID = 6;
    int invalidCountOfFilms = maxID + 1;

    @Test
    public void requestFilmsOk() {
        given()
                .spec(requestSpec)
                .when()
                .get(path)
                .then()
                .statusCode(200);
    }

    @Test
    public void requestFilmWithValidIdIsOk() {
        given()
                .spec(requestSpec)
                .when()
                .get(path + maxID)
                .then()
                .statusCode(200);
    }


    @Test
    public void haveAccessToFilmByID() {
        given()
                .spec(requestSpec)
                .when()
                .get(path + firstID)
                .then()
                .statusCode(200)
                .assertThat()
                .body("title", equalTo("A New Hope"));
    }

    @Test
    public void filmMatchesJSONSchema() {

        given()
                .spec(requestSpec)
                .when()
                .get(path + firstID)
                .then()
                .body(matchesJsonSchemaInClasspath("films.schema.json"));
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
    public void invalidCountOfFilmsShouldError() {
        given()
                .spec(requestSpec)
                .when()
                .get(path + invalidCountOfFilms)
                .then()
                .statusCode(404);
    }
}
