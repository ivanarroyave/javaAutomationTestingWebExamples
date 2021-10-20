package org.example.page.base;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import java.util.List;
import static org.example.util.Numbers.THIRTY;

public class BaseSelenium extends BaseSikulix{

    private static final String WINDOWS_CHROMEDRIVER_KEY = "webdriver.chrome.driver";

    protected WebDriver webDriver;

    //Explicit wait.
    private WebDriverWait wait;

    public BaseSelenium(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public WebDriver chromeDriverConnection(String sourcePath){
        System.setProperty(WINDOWS_CHROMEDRIVER_KEY, sourcePath);
        webDriver = new ChromeDriver();
        wait = new WebDriverWait(webDriver, THIRTY.getValue());
        return webDriver;
    }

    public void setWebdriverUrl(String url){
        webDriver.get(url);
    }

    public void webdriverQuit(){
        webDriver.quit();
    }

    public WebElement findElement(By locator){
        return webDriver.findElement(locator);
    }

    public List<WebElement> findElements(By locator){
        return webDriver.findElements(locator);
    }

    public String getText(WebElement element){
        return element.getText();
    }

    public String getText(By locator){
        return webDriver.findElement(locator).getText();
    }

    public void typeInto(By locator, CharSequence... keysToSend){
        webDriver.findElement(locator).sendKeys(keysToSend);
    }

    public void scrollTo(By locator){
        JavascriptExecutor jse = (JavascriptExecutor)webDriver;
        jse.executeScript("arguments[0].scrollIntoView();",webDriver.findElement(locator));
    }

    public void withExplicitWaitTypeInto(By locator, CharSequence... keysToSend){
        wait.until(presenceOfElementLocated(locator)).sendKeys(keysToSend);
    }

    public void clickOn(By locator){
        webDriver.findElement(locator).click();
    }

    public void withExplicitWaitClickOn(By locator){
        wait.until(presenceOfElementLocated(locator)).click();
    }

    public void withExplicitWaitClear(By locator){
        wait.until(presenceOfElementLocated(locator)).clear();
    }

    public boolean isDisplayed(By locator) throws NoSuchElementException {
        return webDriver.findElement(locator).isDisplayed();
    }

    public List<WebElement> javascriptExecutorWithReturnCollection(String script){
        JavascriptExecutor jse = (JavascriptExecutor)webDriver;
        return (List<WebElement>)jse.executeScript(script);
    }

    public WebElement javascriptExecutorWithReturn(String script){
        JavascriptExecutor jse = (JavascriptExecutor)webDriver;
        return (WebElement)jse.executeScript(script);
    }

    public void javascriptExecutor(String script){
        JavascriptExecutor jse = (JavascriptExecutor)webDriver;
        jse.executeScript(script);
    }

}
