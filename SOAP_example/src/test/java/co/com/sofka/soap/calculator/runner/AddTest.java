package co.com.sofka.soap.calculator.runner;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import static co.com.sofka.soap.question.ReturnStringValue.systemValue;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;

@RunWith(SerenityRunner.class)
public class AddTest {

    private static final String URL_BASE = "http://www.dneonline.com";

    @Test
   public void testAdd(){
        String resource = "/calculator.asmx";
        String bodyRequest = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:tem=\"http://tempuri.org/\">\n" +
                "   <soap:Header/>\n" +
                "   <soap:Body>\n" +
                "      <tem:Add>\n" +
                "         <tem:intA>3</tem:intA>\n" +
                "         <tem:intB>2</tem:intB>\n" +
                "      </tem:Add>\n" +
                "   </soap:Body>\n" +
                "</soap:Envelope>";
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("Content-Type", "application/soap+xml;charset=UTF-8");
        headers.put("action", "http://tempuri.org/Add");

        Actor actor = Actor.named("Iván");
        actor.can(CallAnApi.at(URL_BASE));

        actor.attemptsTo(
                Post.to(resource)
                        .with(
                                req -> req.relaxedHTTPSValidation()
                                        .headers(headers)
                                        .body(bodyRequest)
                        )
        );

        System.out.println(LastResponse.received().answeredBy(actor).asString());
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
                        containsString("<AddResult>5</AddResult>")
                )
        );
    }

}
