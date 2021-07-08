package co.com.company.stepdefinitions.textbox;

import co.com.company.model.TextBoxModel;
import co.com.company.page.TextBoxPage;
import co.com.company.stepdefinitions.Setup;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.apache.log4j.Logger;

import static co.com.company.util.TransposeDataTable.transposeDataTable;
import static net.serenitybdd.assertions.assertj.WebElementStateAssert.assertThatElement;

public class FillTextBoxStepDefinition extends Setup {

    private static final Logger LOGGER = Logger.getLogger(FillTextBoxStepDefinition.class);

    private TextBoxModel textBoxModel;

    @Steps
    public TextBoxPage textBoxPage;

    @Given("I'm a normal user on web page of demoqa")
    public void iMANormalUserOnWebPageOfDemoqa() {
        landingPage.open();
        LOGGER.info("Browse to: " + browser.getCurrentUrl());
    }

    @When("I write any text in every text box and submit it")
    public void iWriteAnyTextInEveryTextBoxAndSubmitIt(DataTable dataTable) {
        textBoxModel = transposeDataTable(TextBoxModel.class, dataTable);

        textBoxPage.clickOnElements();
        textBoxPage.clickOnTextBoxItem();
        textBoxPage.insertIntoUserName(textBoxModel.getFullName());
        textBoxPage.insertIntoEmail(textBoxModel.getEmail());
        textBoxPage.insertIntoCurrentAddress(textBoxModel.getCurrentAddress());
        textBoxPage.insertIntoPermanentAddress(textBoxModel.getPermanentAddress());
        textBoxPage.submit();
    }

    @Then("a section should appear with submitted data")
    public void aSectionShouldAppearWithSubmittedData() {
        assertThatElement(textBoxPage.getOutput())
                .containsText(textBoxModel.getFullName())
                .containsText(textBoxModel.getEmail())
                .containsText(textBoxModel.getCurrentAddress())
                .containsText(textBoxModel.getPermanentAddress());
    }
}
