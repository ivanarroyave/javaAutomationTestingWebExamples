package co.com.screenplay.tasks.practiceform;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.conditions.Check;

import static co.com.screenplay.userinterfaces.practiceform.PracticeForm.*;
import static co.com.screenplay.utils.Gender.*;

public class FillPracticeForm implements Task {

    private String firstName;
    private String lastName;
    private String gender;
    private String mobile;

    public FillPracticeForm usingFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public FillPracticeForm usingLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public FillPracticeForm usingGender(String gender) {
        this.gender = gender;
        return this;
    }

    public FillPracticeForm usingMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(this.firstName).into(FIRST_NAME),
                Enter.theValue(this.lastName).into(LAST_NAME),
                Check.whether(MALE.getValue().equals(this.gender))
                        .andIfSo(Click.on(GENDER_MALE))
                        .otherwise(
                                Check.whether(FEMALE.getValue().equals(this.gender))
                                    .andIfSo(Click.on(GENDER_FEMALE))
                                    .otherwise(Click.on(GENDER_OTHER))
                        ),
                Enter.theValue(this.mobile).into(MOBILE),
                Scroll.to(SUBMIT),
                Click.on(SUBMIT)
        );
    }

    public static FillPracticeForm fillPracticeForm(){
        return new FillPracticeForm();
    }

}
