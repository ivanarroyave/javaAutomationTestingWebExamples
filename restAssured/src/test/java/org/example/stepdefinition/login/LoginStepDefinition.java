package org.example.stepdefinition.login;

import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;

public class LoginStepDefinition {
    private static final String BASE_URI = "https://reqres.in";
    private static final String BASE_PATH= "/api";
    private static final String RESOURCE= "/login";
    private Response response;
    private RequestSpecification requestSpecification;

    @Before
    public void init() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.basePath = BASE_PATH;
    }

    @Dado("que el usuario está en la página de inicio de sesión con el correo de usuario {string} y la contraseña {string}")
    public void queElUsuarioEstaEnLaPaginaDeInicioDeSesionConElCorreoDeUsuarioYLaContrasena(String email, String password) {
        requestSpecification =
                given()
                    .log()
                    .all(true)
                    .contentType(ContentType.JSON)
                    .body(
                            "{\n" +
                            "    \"email\": \"" + email + "\",\n" +
                            "    \"password\": \"" + password + "\"\n" +
                            "}"
                    );
    }

    @Cuando("el usuario hace una petición de inicio")
    public void elUsuarioHaceUnaPeticionDeInicio() {
        response = requestSpecification
                .when()
                    .post(RESOURCE);
    }

    @Entonces("el usuario deberá ver un código de respuesta exitoso y un token de respuesta")
    public void elUsuarioDeberaVerUnCodigoDeRespuestaExitosoYUnTokenDeRespuesta() {
        response
                .then()
                    .log()
                .all(true)
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("token", notNullValue())
                .body("token", is("QpwL5tke4Pnpja7X4"))
                .body(matchesJsonSchemaInClasspath("./schemas/login.json"));
    }

}
