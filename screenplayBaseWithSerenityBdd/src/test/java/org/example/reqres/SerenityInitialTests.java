package org.example.reqres;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.example.models.Datum;
import org.example.models.Users;
import org.example.questions.GetUsersQuestion;
import org.junit.*;
import org.junit.runner.RunWith;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.example.questions.ResponseCode.was;
import static org.example.tasks.GetUsers.getUsers;
import static org.example.util.Numbers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SerenityRunner.class)
public class SerenityInitialTests {
    private static final String restApiUrl = "https://reqres.in/api";
    private static final String resource = "/users?page=2";
    Actor dario;
    @Before
    public void setUp(){
        dario = Actor.named("Dario").whoCan(CallAnApi.at(restApiUrl));
    }

    @Test
    public void getTheUsers(){
        dario.attemptsTo(
                getUsers().onPage(TWO.getValue())
        );

        Datum user = new GetUsersQuestion().answeredBy(dario).getData().stream().filter(
                x -> x.getId() == 1
        ).findFirst().orElse(null);

        dario.should(
                seeThat("the response code", was(), equalTo(TWO_HUNDRED.getValue()))
        );

        dario.should(
                seeThat("user is not null", act -> user, notNullValue())
        );

/*        dario.should(
                seeThat("user is not null", act -> user.getName(), equalTo("fuchsia rose")),
                seeThat("user is not null", act -> user.getPantoneValue(), equalTo("17-2031"))
        );*/


    }

}
