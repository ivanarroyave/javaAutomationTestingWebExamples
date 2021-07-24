package org.example.toolsqa;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import static com.google.common.base.StandardSystemProperty.USER_DIR;

public class TestPracticeForm {

    private static final String URL_BASE_TO_TEST = "https://demoqa.com/";
    private WebDriver webDriver;
    private org.example.page.practiceform.PracticeForm practiceForm;
    private static final String WINDOWS_CHROMEDRIVER = USER_DIR.value() + "/src/test/resources/webdriver/windows/chromedriver.exe";

    @Before
    public void setUp(){
        practiceForm = new org.example.page.practiceform.PracticeForm(webDriver);
        webDriver = practiceForm.chromeDriverConnection(WINDOWS_CHROMEDRIVER);
        practiceForm.setWebdriverUrl(URL_BASE_TO_TEST);
    }

    @After
    public void tearDown(){
        practiceForm.webdriverQuit();
    }

    @Test
    public void test(){
        practiceForm.studentRegistrationForm();
        Assert.assertTrue(practiceForm.message(), practiceForm.validation());
      }
}
