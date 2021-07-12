package co.com.company.stepdefinition.practiceform;

import co.com.company.stepdefinition.Setup;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static co.com.screenplay.tasks.practiceform.BrowseToPracticeForm.browseToPracticeForm;
import static co.com.screenplay.tasks.practiceform.OpenLandingPage.openLandingPage;
import static net.serenitybdd.screenplay.actors.OnStage.*;

public class PracticeFormStepDefinition extends Setup {

    private static final String ACTOR_NAME = "Student";

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
    public void himHasFilledItAndSubmitted(io.cucumber.datatable.DataTable dataTable) {

    }

    @Then("the student will see a registration information")
    public void theStudentWillSeeARegistrationInformation() {
    }

}
