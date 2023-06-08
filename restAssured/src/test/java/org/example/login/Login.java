package org.example.login;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class Login {
    private static final String BASE_URI = "https://reqres.in";
    private static final String BASE_PATH= "/api";
    private static final String RESOURCE= "/login";

    @Before
    public void init() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.basePath = BASE_PATH;
    }

    @Test
    public void test() {
        given()
                .log()
                .all(true)
                .contentType(ContentType.JSON)
                .body("{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"cityslicka\"\n" +
                "}")
        .when()
                .post(RESOURCE)
        .then()
                .log()
                .all(true)
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("token", notNullValue())
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }
}
