package org.example.login;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.*;

public class User {
    private static final String BASE_URI = "https://reqres.in";
    private static final String BASE_PATH= "/api";
    private static final String RESOURCE= "/users?page=2";

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
                .when()
                .get(RESOURCE)
                .then()
                .log()
                .all(true)
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("per_page", is(6))
                .body("data.email[1]", is("lindsay.ferguson@reqres.in"))
                .body("data.findAll {it.id > 0 }.id", hasItems(7, 8, 11))
                .body("data.findAll {it}.email", hasItems("george.edwards@reqres.in", "rachel.howell@reqres.in"))
                .body("data.find {it.email == 'rachel.howell@reqres.in'}.email", equalTo("rachel.howell@reqres.in"))
                .body(matchesJsonSchemaInClasspath("./schemas/userList.json"));
    }
}
