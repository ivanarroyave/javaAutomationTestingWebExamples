package co.com.screenplay.questions.practiceform;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static co.com.screenplay.userinterfaces.practiceform.PracticeForm.*;

public class PracticeForm implements Question<Boolean> {

    private String firstName;
    private String lastName;
    private String gender;
    private String mobile;

    public PracticeForm wasFilledWithFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PracticeForm andWithLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PracticeForm andWithGender(String gender) {
        this.gender = gender;
        return this;
    }

    public PracticeForm andWithMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public PracticeForm is(){
        return this;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return (STUDENT_NAME_VALIDATION.resolveFor(actor).containsOnlyText(firstName + " " + lastName)
                && GENDER_VALIDATION.resolveFor(actor).containsOnlyText(gender)
                && MOBILE_VALIDATION.resolveFor(actor).containsOnlyText(mobile)
        );
    }

    public static PracticeForm practiceForm(){
        return new PracticeForm();
    }
}
