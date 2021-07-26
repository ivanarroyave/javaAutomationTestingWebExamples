package org.example.tasks.landingpage;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import org.example.userinterfaces.landingpage.LandingPage;

public class GoToLandingPage implements Task {

    private LandingPage landingPage;
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.browserOn(landingPage)
        );
    }

    public static GoToLandingPage goToLandingPage(){
        return new GoToLandingPage();
    }
}
