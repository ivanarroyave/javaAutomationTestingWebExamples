package co.com.company.stepdefinitions;

import co.com.company.page.LandingPage;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.openqa.selenium.WebDriver;

public class Setup {
    @Managed()
    protected WebDriver browser;

    @Steps
    protected LandingPage landingPage;
}
