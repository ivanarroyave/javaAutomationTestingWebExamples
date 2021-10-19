package co.com.sofka.soap.calculator.stepdefinitions.calculadora;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import static co.com.sofka.soap.question.ReturnStringValue.systemValue;
import static co.com.sofka.soap.utils.FileUtilities.readFile;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;

public class AddWithCucumberStepDefinition {

    private static final String URL_BASE = "http://www.dneonline.com";
    private static final String RESOURCE = "/calculator.asmx";
    private static final String ADD_XML = System.getProperty("user.dir") + "\\src\\test\\resources\\calculator\\add.xml";
    private static final String INT_A = "[intA]";
    private static final String INT_B = "[intB]";
    private String bodyRequest;
    Actor actor;

    @Given("que el usuario de la calculadaora ha definido como sumandos el {int} y el {int}")
    public void queElUsuarioDeLaCalculadaoraHaDefinidoComoSumandos(Integer intA, Integer intB) {

        actor = Actor.named("Iván");
        actor.can(CallAnApi.at(URL_BASE));

        bodyRequest = readFile(ADD_XML)
                .replace(INT_A, intA.toString())
                .replace(INT_B, intB.toString());
    }

    @When("el usuario de la calculadora ejecuta el cálculo")
    public void elUsuarioDeLaCalculadoraEjecutaElCalculo() {
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("Content-Type", "application/soap+xml;charset=UTF-8");
        headers.put("action", "http://tempuri.org/Add");

        actor.attemptsTo(
                Post.to(RESOURCE)
                        .with(
                                req -> req.relaxedHTTPSValidation()
                                        .headers(headers)
                                        .body(bodyRequest)
                        )
        );
    }

    @Then("el usuario debería obtener el resultado de {int}")
    public void elUsuarioDeberiaObtenerElResultadoDe(Integer total) {
        String soapResponse = new String(LastResponse.received().answeredBy(actor).asByteArray(), StandardCharsets.UTF_8);
        actor.should(
                seeThatResponse(
                        "El código de respuesta HTTP debe ser: ",
                        response -> response
                                .statusCode(200)
                ),
                seeThat(
                        "El resultado de la suma debe ser: ",
                        systemValue(soapResponse),
                        containsString("<AddResult>" + total + "</AddResult>")
                )
        );
    }
}
