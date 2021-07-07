package co.com.company.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {"src/test/resources/features/fillTextBox.feature"},
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        glue = {"co.com.company.stepdefinitions"},
        tags = {"not @ignore"}
)
public class FillTextBox {
    @BeforeClass
    public static void setup(){
    }
}
