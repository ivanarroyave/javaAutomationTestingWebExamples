package org.example.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.example.models.Datum;
import org.example.models.Users;

public class GetUsersQuestion implements Question<Users> {
    @Override
    public Users answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(Users.class);
    }
}
