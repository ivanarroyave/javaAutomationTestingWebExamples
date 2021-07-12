package co.com.screenplay.userinterfaces.practiceform;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;

public class PracticeForm extends PageObject {
    //For browse elements.
    public static final Target ELEMENTS = Target
            .the("Target")
            .located(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div/div[1]/div/div[3]/h5"));

    public static final Target FORMS = Target
            .the("Form")
            .located(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[1]/div/div/div[2]/span/div/div[1]/span"));

    public static final Target PRACTICE_FORM = Target
            .the("Practice Form")
            .located(By.xpath("//*[@id=\"item-0\"]/span"));

    //For fill elements.
    public static final Target FIRST_NAME = Target
            .the("First name")
            .located(By.id("firstName"));

    public static final Target LAST_NAME = Target
            .the("Last name")
            .located(By.id("lastName"));

    public static final Target GENDER_MALE = Target
            .the("Gender male")
            .located(By.id("gender-radio-1"));

    public static final Target GENDER_FEMALE = Target
            .the("Gender female")
            .located(By.id("gender-radio-2"));

    public static final Target GENDER_OTHER = Target
            .the("Gender other")
            .located(By.id("gender-radio-3"));

    public static final Target MOBILE = Target
            .the("Mobile")
            .located(By.id("gender-radio-3"));

    public static final Target SUBMIT = Target
            .the("Submit")
            .located(By.id("submit"));

    //For validations.
    public static final Target STUDENT_NAME_VALIDATION = Target
            .the("Student Name")
            .located(By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[1]/td[2]"));

    public static final Target GENDER_VALIDATION = Target
            .the("Gender")
            .located(By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[3]/td[2]"));

    public static final Target MOBILE_VALIDATION = Target
            .the("Gender")
            .located(By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[4]/td[2]"));

    public static final Target DATE_OF_BIRTH_VALIDATION = Target
            .the("Gender")
            .located(By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[5]/td[2]"));

}
