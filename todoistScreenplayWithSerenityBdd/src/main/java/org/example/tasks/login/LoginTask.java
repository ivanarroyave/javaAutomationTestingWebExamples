package org.example.tasks.login;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static org.example.userinterfaces.login.Login.*;

public class LoginTask implements Task {

    private String userName;
    private String password;

    public LoginTask usingTheUser(String userName) {
        this.userName = userName;
        return this;
    }

    public LoginTask andThePassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(LOGIN_LINK),
                Enter.theValue(userName).into(EMAIL),
                Enter.theValue(password).into(PASSWORD),
                Click.on(LOGIN_BUTTON)
        );
    }

    public static LoginTask login(){
        return new LoginTask();
    }
}
