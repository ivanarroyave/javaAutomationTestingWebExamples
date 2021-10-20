package co.com.sofka.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnStringValue implements Question<String> {

    private final String systemValue;

    private ReturnStringValue(String systemValue) {
        this.systemValue = systemValue;
    }

    @Override
    public String answeredBy(Actor actor) {
        return systemValue;
    }

    public static ReturnStringValue systemValue(String systemValue){
        return new ReturnStringValue(systemValue);
    }

}
