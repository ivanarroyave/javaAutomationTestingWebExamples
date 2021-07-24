package org.example.page.practiceform;

import org.example.model.Student;
import org.example.page.base.BaseSelenium;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

import static com.google.common.base.StandardSystemProperty.USER_DIR;

public class PracticeForm extends BaseSelenium {

    //Model for a student.
    private final Student student = new Student();

    //Browsing to the form.
    private final By forms = By.xpath("//h5[text() = 'Forms']");
    private final By practiceFormElement = By.xpath("//span[text() = 'Practice Form']");

    //Filling the form.
    private final By firstName = By.id("firstName");
    private final By lastName = By.id("lastName");
    private final By userEmail = By.id("userEmail");
    private final By male = By.xpath("//label[text()='Male']");
    private final By userNumber = By.id("userNumber");

    private final By dateOfBirthInput = By.id("dateOfBirthInput");
    private final By month = By.xpath("//option[. = '" + student.getDateOfBirthMonth() + "']");
    private final By year = By.xpath("//option[. = '" + student.getDateOfBirthYear() + "']");
    private final By day = By.cssSelector(".react-datepicker__day--" + student.getDateOfBirthDayInFormat() + ":nth-child(1)");

    private final By subjectsInput = By.id("subjectsInput");
    private final By music = By.xpath("//label[text()='Music']");
    private final By currentAddress = By.id("currentAddress");
    private final By state = By.id("react-select-3-input");
    private final By city = By.id("react-select-4-input");

    //Submitting the form.
    private final By submit = By.id("submit");

    //Sikulix elements.
    private static final String ATTACHMENT_FILE_PATCH = USER_DIR.value() + "\\src\\test\\resources\\practiceform\\attachments\\smile-icon.jpg";

    private static final String PAGE_BASE_PATCH = USER_DIR.value() + "\\src\\main\\resources\\page\\practiceform\\";
    private static final String SELECT_PICTURE_PATCH = PAGE_BASE_PATCH + "selectPicture.PNG";
    private static final String SELECT_OPEN_PATCH = PAGE_BASE_PATCH + "openWindows.PNG";
    private static final String FILE_NAME_TEXT_BOX_PATCH = PAGE_BASE_PATCH + "fileNameWindows.PNG";

    //For asserts.
    private final By studentName =        By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[1]/td[2]");
    private final By studentEmail =       By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[2]/td[2]");
    private final By gender =             By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[3]/td[2]");
    private final By mobile =             By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[4]/td[2]");
    private final By studentDateOfBirth = By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[5]/td[2]");
    private final By subjects =           By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[6]/td[2]");
    private final By hobbies =            By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[7]/td[2]");
    private final By picture =            By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[8]/td[2]");
    private final By address =            By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[9]/td[2]");
    private final By stateAndCity =      By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[10]/td[2]");

    public PracticeForm(WebDriver driver) {
        super(driver);
    }

    public void studentRegistrationForm(){
        browseToPracticeForm();
        fillPracticeForm();
    }

    private void browseToPracticeForm(){
        clickOn(forms);
        clickOn(practiceFormElement);
    }

    private void fillPracticeForm(){
        scrollTo(firstName);
        withExplicitWaitClear(firstName);
        withExplicitWaitTypeInto(firstName, student.getFirstName());

        scrollTo(lastName);
        withExplicitWaitClear(lastName);
        withExplicitWaitTypeInto(lastName, student.getLastName());

        scrollTo(userEmail);
        withExplicitWaitClear(userEmail);
        withExplicitWaitTypeInto(userEmail, student.getEmail());

        scrollTo(male);
        clickOn(male);

        scrollTo(userNumber);
        withExplicitWaitClear(userNumber);
        withExplicitWaitTypeInto(userNumber, student.getMobile());

        scrollTo(dateOfBirthInput);
        clickOn(dateOfBirthInput);
        clickOn(month);
        clickOn(year);
        clickOn(day);

        scrollTo(subjectsInput);
        withExplicitWaitTypeInto(subjectsInput, student.getSubjects());
        withExplicitWaitTypeInto(subjectsInput, Keys.TAB);

        scrollTo(music);
        clickOn(music);

        clickOn(SELECT_PICTURE_PATCH);
        insertInto(FILE_NAME_TEXT_BOX_PATCH, ATTACHMENT_FILE_PATCH);
        clickOn(SELECT_OPEN_PATCH);

        scrollTo(currentAddress);
        withExplicitWaitClear(currentAddress);
        withExplicitWaitTypeInto(currentAddress, student.getAddress());

        scrollTo(state);
        withExplicitWaitTypeInto(state, student.getState(), Keys.TAB);

        scrollTo(city);
        withExplicitWaitTypeInto(city, student.getCity(), Keys.TAB);

        scrollTo(submit);
        clickOn(submit);
    }

    private Set<String> dataEntered(){
        Set<String> dataEntered = new HashSet<>();
        dataEntered.add(student.getFirstName() + " " + student.getLastName());
        dataEntered.add(student.getEmail());
        dataEntered.add(student.getGender());
        dataEntered.add(student.getMobile());
        dataEntered.add(student.getDateOfBirthDay() + " " + student.getDateOfBirthMonth() + "," + student.getDateOfBirthYear());
        dataEntered.add(student.getSubjects());
        dataEntered.add(student.getHobbies());
        dataEntered.add(student.getPicture());
        dataEntered.add(student.getAddress());
        dataEntered.add(student.getState() + " " + student.getCity());
        return dataEntered;
    }

    private Set<String> systemOutcome(){
        Set<String> summary = new HashSet<>();
        summary.add(getText(studentName));
        summary.add(getText(studentEmail));
        summary.add(getText(gender));
        summary.add(getText(mobile));
        summary.add(getText(studentDateOfBirth));
        summary.add(getText(subjects));
        summary.add(getText(hobbies));
        summary.add(getText(picture));
        summary.add(getText(address));
        summary.add(getText(stateAndCity));
        return summary;
    }

    public boolean validation(){
        return systemOutcome().containsAll(dataEntered());
    }

    public String message(){
        return "\nData entered:\n"
                + dataEntered()
                + "\nOutcome:\n"
                + systemOutcome();
    }
}
