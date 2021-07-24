package co.com.company.page;

import net.serenitybdd.assertions.assertj.WebElementStateAssert;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;

import java.nio.charset.StandardCharsets;

import static net.serenitybdd.assertions.assertj.WebElementStateAssert.assertThatElement;

public class TextBoxPage extends PageObject {
    @FindBy(xpath = "//*[@id=\"app\"]/div/div/div[2]/div/div[1]")
    private WebElementFacade elements;

    @FindBy(id = "item-0")
    private WebElementFacade textBoxItem;

    @FindBy(id = "userName")
    private WebElementFacade fullName;

    @FindBy(id = "userEmail")
    private WebElementFacade email;

    @FindBy(id = "currentAddress")
    private WebElementFacade currentAddress;

    @FindBy(id = "permanentAddress")
    private WebElementFacade permanentAddress;

    @FindBy(id = "submit")
    private WebElementFacade submit;

    @FindBy(id = "output")
    private WebElementFacade output;

    public WebElementFacade getOutput() {
        return output;
    }

    @Step
    public void clickOnElements(){
        elements.click();
    }

    @Step
    public void clickOnTextBoxItem(){
        textBoxItem.click();
    }

    @Step
    public void insertIntoUserName(String text){
        insertIntoTextBox(text, fullName);
    }

    @Step
    public void insertIntoEmail(String text){
        insertIntoTextBox(text, email);
    }

    @Step
    public void insertIntoCurrentAddress(String text){
        insertIntoTextBox(text, currentAddress);
    }

    @Step
    public void insertIntoPermanentAddress(String text){
        insertIntoTextBox(text, permanentAddress);
    }

    @Step
    public void submit(){
        submit.click();
    }

    private void insertIntoTextBox(String text, WebElementFacade textBox){
        textBox.setWindowFocus();
        textBox.type(text);
    }
}
