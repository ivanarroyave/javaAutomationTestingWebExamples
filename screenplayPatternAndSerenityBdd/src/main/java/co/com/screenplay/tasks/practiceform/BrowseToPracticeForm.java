package co.com.screenplay.tasks.practiceform;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Scroll;

import static co.com.screenplay.userinterfaces.practiceform.PracticeForm.ELEMENTS;
import static co.com.screenplay.userinterfaces.practiceform.PracticeForm.FORMS;
import static co.com.screenplay.userinterfaces.practiceform.PracticeForm.PRACTICE_FORM;

public class BrowseToPracticeForm implements Task {
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Click.on(ELEMENTS),
            Scroll.to(FORMS),
            Click.on(FORMS),
            Click.on(PRACTICE_FORM)
        );
    }

    public static BrowseToPracticeForm browseToPracticeForm(){
        return new BrowseToPracticeForm();
    }

}
