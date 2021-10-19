package co.com.sofka.soap.calculator.runner;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberSerenityRunner;
import org.junit.runner.RunWith;

@RunWith(CucumberSerenityRunner.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/test/resources/features/calculator/Add.feature"},
        glue = {"co.com.sofka.soap.calculator.stepdefinitions.calculadora"}
)
public class AddWithCucumberTest {
}
