package org.example.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

public class ResponseCode implements Question<Integer>{

    @Override
    public Integer answeredBy(Actor actor) {
        return lastResponse().statusCode();
    }

    public static ResponseCode was(){
        return new ResponseCode();
    }

}
