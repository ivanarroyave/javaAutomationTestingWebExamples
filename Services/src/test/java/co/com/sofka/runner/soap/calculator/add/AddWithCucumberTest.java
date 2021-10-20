package co.com.sofka.runner.soap.calculator.add;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberSerenityRunner;
import org.junit.runner.RunWith;

@RunWith(CucumberSerenityRunner.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/test/resources/features/calculator/add.feature"},
        glue = {"co.com.sofka.stepdefnitions.soap.calculator.add"}
)
public class AddWithCucumberTest {
}
