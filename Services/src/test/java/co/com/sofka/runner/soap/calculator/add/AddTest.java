package co.com.sofka.runner.soap.calculator.add;

import co.com.sofka.stepdefnitions.soap.calculator.SetUp;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import static co.com.sofka.questions.ReturnStringValue.systemValue;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;

@RunWith(SerenityRunner.class)
public class AddTest extends SetUp {

    @Test
    public void testAdd(){
        String bodyRequest = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <tem:Add>\n" +
                "         <tem:intA>10</tem:intA>\n" +
                "         <tem:intB>5</tem:intB>\n" +
                "      </tem:Add>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        Actor actor = Actor.named("Iván");
        actor.can(CallAnApi.at(URL_BASE));

        actor.attemptsTo(
                Post.to(RESOURCE)
                        .with(
                                req -> req.relaxedHTTPSValidation()
                                        .headers(headers())
                                        .body(bodyRequest)
                        )
        );

        String soapResponse = new String(LastResponse.received().answeredBy(actor).asByteArray(), StandardCharsets.UTF_8);

        actor.should(
                seeThatResponse(
                        "El código de respuesta HTTP debe ser: ",
                        response -> response.statusCode(HttpStatus.SC_OK)
                ),
                seeThat(
                        "El resultado de la suma debe ser: ",
                        systemValue(soapResponse),
                        containsString("<AddResult>15</AddResult>")
                )
        );

    }

}
