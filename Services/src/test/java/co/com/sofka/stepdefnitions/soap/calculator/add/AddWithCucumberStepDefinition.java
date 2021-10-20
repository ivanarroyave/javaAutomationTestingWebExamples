package co.com.sofka.stepdefnitions.soap.calculator.add;

import co.com.sofka.stepdefnitions.soap.calculator.SetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static co.com.sofka.questions.ReturnStringValue.systemValue;
import static co.com.sofka.tasks.calculator.DoPost.doPost;
import static co.com.sofka.util.FileUtilities.readFile;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.containsString;

public class AddWithCucumberStepDefinition extends SetUp {

    private static final String ADD_XML = System.getProperty("user.dir") + "\\src\\test\\resources\\calculator\\add.xml";
    private static final String INT_A = "[intA]";
    private static final String INT_B = "[intB]";

    @Given("que el usuario de la calculadora ha definido como sumandos el {int} y el {int}")
    public void queElUsuarioDeLaCalculadoraHaDefinidoComoSumandosElYEl(Integer intA, Integer intB) {
        setUp();
        bodyRequest = defineBodyRequest(intA, intB);
    }

    @When("el usuario de la calculadora ejecuta el cálculo")
    public void elUsuarioDeLaCalculadoraEjecutaElCalculo() {
        actor.attemptsTo(
                doPost().
                        usingThe(RESOURCE).
                        with(headers()).
                        and(bodyRequest)
        );
    }

    @Then("el ususario debería obtener el resultado {int}")
    public void elUsusarioDeberiaObtenerElResultado(Integer total) {
        actor.should(
                seeThatResponse(
                        "El código de respuesta HTTP debe ser: " + SC_OK,
                        response -> response
                                .statusCode(SC_OK)
                ),
                seeThat(
                        "El resultado de la suma debe ser: ",
                        systemValue(fromLastResponseBy(actor)),
                        containsString("<AddResult>" + total + "</AddResult>")
                )
        );
    }

    private String defineBodyRequest(Integer intA, Integer intB){
        return readFile(ADD_XML)
                .replace(INT_A, intA.toString())
                .replace(INT_B, intB.toString());
    }

}
