package org.example.login.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.exceptions.ValidationOnPage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.example.exceptions.ValidationOnPage.THE_ELEMENT_ON_PAGE_DO_NOT_EXIST;
import static org.example.questions.TodayLandingPage.todayLandingPage;
import static org.example.tasks.landingpage.GoToLandingPage.goToLandingPage;
import static org.example.tasks.login.LoginTask.login;
import static org.example.userinterfaces.login.Login.TODAY;
import static org.example.util.BooleanConvertion.EXISTING;
import static org.hamcrest.Matchers.is;

public class Login extends Setup{

    @Given("the user is on landing page")
    public void theUserIsOnLandingPage() {
        generalSetup();
        theActorInTheSpotlight().attemptsTo(
                goToLandingPage()
        );
    }

    @When("the user insert the credential")
    public void theUserInsertTheCredential(io.cucumber.datatable.DataTable dataTable) {
        String user = dataTable.row(0).get(1);
        String password = dataTable.row(1).get(1);
        theActorInTheSpotlight().attemptsTo(
                login().usingTheUser(user).andThePassword(password)
        );
    }

    @Then("the user sould see the the Add task page")
    public void theUserSouldSeeTheTheAddTaskPage() {
        theActorInTheSpotlight().should(
                seeThat(todayLandingPage(),
                        is(EXISTING.getValue())).orComplainWith(ValidationOnPage.class, messageForException())
        );
    }

    private String messageForException(){
        return String.format(THE_ELEMENT_ON_PAGE_DO_NOT_EXIST, TODAY.getName() + " on " + webDriver.getCurrentUrl());
    }
}
