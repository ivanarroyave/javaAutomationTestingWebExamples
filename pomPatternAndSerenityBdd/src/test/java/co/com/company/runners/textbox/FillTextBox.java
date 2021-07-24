package co.com.company.runners.textbox;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import static co.com.company.util.Log4jValues.LOG4J_PROPERTIES_FILE_PATH;
import static com.google.common.base.StandardSystemProperty.USER_DIR;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {"src/test/resources/features/textbox/fillTextBox.feature"},
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        glue = {"co.com.company.stepdefinitions.textbox",
                "co.com.company.util"},
        tags = "not @ignore"
)
public class FillTextBox {
    @Before
    public static void test(){
        PropertyConfigurator.configure(USER_DIR + LOG4J_PROPERTIES_FILE_PATH.getValue());
    }
}
