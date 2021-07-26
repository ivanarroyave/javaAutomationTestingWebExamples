package org.example.tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class GetUsers implements Task {

    private int page;

    public GetUsers onPage(int page) {
        this.page = page;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String resource = "/users?page=";
        actor.attemptsTo(
                Get.resource(resource + page).with(
                        requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                                .header("header1", "value1")
                )
        );
    }
    public static GetUsers getUsers(){
        return new GetUsers();
    }
}
