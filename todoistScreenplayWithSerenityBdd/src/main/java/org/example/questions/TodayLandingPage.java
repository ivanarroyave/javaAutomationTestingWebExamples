package org.example.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static org.example.userinterfaces.login.Login.TODAY;

public class TodayLandingPage implements Question<Boolean> {
    @Override
    public Boolean answeredBy(Actor actor) {
        return TODAY.resolveFor(actor).isVisible();
    }

    public static TodayLandingPage todayLandingPage(){
        return new TodayLandingPage();
    }
}
