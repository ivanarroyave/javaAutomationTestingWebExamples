package org.example.stepdefinitions.setup;

import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

public class Setup {

    @Managed
    protected WebDriver webDriver;

    private void setUpBrowser(){
        webDriver.manage().window().maximize();
    }

    private void TheUser(String user, WebDriver webDriver){
        OnStage.setTheStage(new OnlineCast());
        theActorCalled(user).can(BrowseTheWeb.with(webDriver));
    }

    public void generalSetup(String name){
        setUpBrowser();
        TheUser(name, webDriver);
    }
}
