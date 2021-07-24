package co.com.company.stepdefinition.practiceform;

import co.com.company.stepdefinition.Setup;
import co.com.screenplay.exceptions.practiceform.ValidationTextDoNotMatch;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static co.com.screenplay.exceptions.practiceform.ValidationTextDoNotMatch.VALIDATION_DO_NOT_MATCH;
import static co.com.screenplay.questions.practiceform.PracticeForm.practiceForm;
import static co.com.screenplay.tasks.practiceform.FillPracticeForm.fillPracticeForm;
import static co.com.screenplay.tasks.practiceform.BrowseToPracticeForm.browseToPracticeForm;
import static co.com.screenplay.tasks.landingpage.OpenLandingPage.openLandingPage;
import static co.com.screenplay.userinterfaces.practiceform.PracticeForm.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class PracticeFormStepDefinition extends Setup {

    private static final String ACTOR_NAME = "Student";
    private DataTable dataToValidate;

    @Given("the student is on landing page of Tools QA")
    public void theStudentIsOnLandingPageOfToolsQA() {
        actorSetupTheBrowser(ACTOR_NAME);
        theActorInTheSpotlight().attemptsTo(
                openLandingPage()
        );
    }

    @When("him browse to registration form")
    public void himBrowseToRegistrationForm() {
        theActorInTheSpotlight().attemptsTo(
                browseToPracticeForm()
        );
    }

    @When("him has filled it and submitted")
    public void himHasFilledItAndSubmitted(DataTable dataTable) {
        dataToValidate = dataTable;
        theActorInTheSpotlight().attemptsTo(
                fillPracticeForm()
                    .usingFirstName(dataTable.row(0).get(1))
                    .usingLastName(dataTable.row(1).get(1))
                    .usingGender(dataTable.row(2).get(1))
                    .usingMobile(dataTable.row(3).get(1))
        );
    }

    @Then("the student will see a registration information")
    public void theStudentWillSeeARegistrationInformation() {
        theActorInTheSpotlight().should(
                seeThat(practiceForm()
                        .wasFilledWithFirstName(dataToValidate.row(0).get(1))
                        .andWithLastName(dataToValidate.row(1).get(1))
                        .andWithGender(dataToValidate.row(2).get(1))
                        .andWithMobile(dataToValidate.row(3).get(1))
                        .is(), equalTo(true))
                .orComplainWith(
                        ValidationTextDoNotMatch.class,
                        String.format(VALIDATION_DO_NOT_MATCH, compareInWithSystemOutcome())
                )
        );
    }

    private String compareInWithSystemOutcome(){
        return "\n" + "Data for test : System outcome"
                + "\n" + dataToValidate.row(0).get(1) + " " + dataToValidate.row(1).get(1) + " : " + STUDENT_NAME_VALIDATION.resolveFor(theActorInTheSpotlight()).getText()
                + "\n" + dataToValidate.row(2).get(1) + " : " + GENDER_VALIDATION.resolveFor(theActorInTheSpotlight()).getText()
                + "\n" + dataToValidate.row(3).get(1) + " : " + MOBILE_VALIDATION.resolveFor(theActorInTheSpotlight()).getText()
                ;
    }

}
