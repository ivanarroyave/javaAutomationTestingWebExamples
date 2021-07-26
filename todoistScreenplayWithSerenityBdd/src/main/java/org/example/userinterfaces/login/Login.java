package org.example.userinterfaces.login;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

import static org.openqa.selenium.By.*;

public class Login extends PageObject {

    public static final Target LOGIN_LINK = Target
            .the("Login")
            .located(linkText("Iniciar sesión"));

    public static final Target EMAIL = Target
            .the("Email")
            .located(id("email"));

    public static final Target PASSWORD = Target
            .the("Password")
            .located(id("password"));

    public static final Target LOGIN_BUTTON = Target
            .the("Login")
            .located(xpath("//*[@id=\"login_form\"]/button"));

    public static final Target TODAY = Target
            .the("Today")
            .located(xpath("//*[@id=\"agenda_view\"]/header/div/h1/span"));

}
