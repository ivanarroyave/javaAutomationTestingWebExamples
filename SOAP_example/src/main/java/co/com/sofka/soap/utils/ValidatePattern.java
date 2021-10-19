package co.com.sofka.soap.utils;

import java.util.regex.Pattern;

public class ValidatePattern {

    private final String sentence;

    private ValidatePattern(String sentence) {
        this.sentence = sentence;
    }

    public static ValidatePattern verifyTheSentence(String sentence) {
        return new ValidatePattern(sentence);
    }

    public boolean withThePattern(String pattern){
        return Pattern.compile(pattern).matcher(sentence).matches();
    }

}
