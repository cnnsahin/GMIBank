package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public abstract class WDController {
//My Driver class is abstract so I do not create an object of this class
//TestBase class is also abstract

    private static int timeout = 5;

    Duration duration;
    private WDController(){
    }
    private static WebDriver driver;
    public static WebDriver getDriver(){
        if(driver==null) {
            switch (ConfigurationReader.getProperty("browser")) {
                case "chrome":
                     driver = new ChromeDriver();
                    break;

                case "firefox":
                     driver = new FirefoxDriver();

                    break;

                case "ie":

                    driver = new InternetExplorerDriver();
                    break;

                case "chrome-headless":
//
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("headless");
                    options.addArguments("disable-gpu");
                    driver = new ChromeDriver(options);
                    break;

            }
        }
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    public static void waitAndClick(WebElement element, int timeout) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.click();
                return;
            } catch (WebDriverException e) {
                wait(1);
            }
        }
    }
    public static void waitAndClick(WebElement element) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.click();
                return;
            } catch (WebDriverException e) {
                wait(1);
            }
        }
    }



    public static void waitAndSendText(WebElement element,String text, int timeout) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.sendKeys(text);
                return;
            } catch (WebDriverException e) {
                wait(1);
            }
        }
    }
    public static void waitAndSendText(WebElement element,String text) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.sendKeys(text);
                return;
            } catch (WebDriverException e) {
                wait(1);
            }
        }
    }
    public static void waitAndSendTextWithDefaultTime(WebElement element,String text) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.sendKeys(text);
                return;
            } catch (WebDriverException e) {
                wait(1);
            }
        }
    }

    public static String waitAndGetText(WebElement element, int timeout) {
        String text="";
        for (int i = 0; i < timeout; i++) {
            try {
                text = element.getText();
                return text;
            } catch (WebDriverException e) {
                wait(1);
            }
        }
        return null;
    }


    //Webdriver
    //ChromeDriver
    //Iedriver
    //FirefoxDriver

    public static void wait2(int sec){
        try {
            Thread.sleep(1000*sec);
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }catch (TimeoutException e){
            e.printStackTrace();
        }catch (StaleElementReferenceException e){
            e.printStackTrace();
        }catch (ElementClickInterceptedException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    //5 seconds
    public static void waitAndClickElement(WebElement element , int seconds){
        for (int i = 0; i < seconds ; i++) {

            try {
                element.click();
                break;
            }catch (Exception e){
                wait2(1);
            }


        }
    }

    public static void closeDriver(){
        if (driver!=null) {
            driver.quit();
            driver=null;
        }
    }

    public static void wait(int secs) {


        try {
            Thread.sleep(1000 * secs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }catch (StaleElementReferenceException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static WebElement waitForVisibility(WebElement element, Duration timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(WDController.getDriver(), timeToWaitInSec);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    public static WebElement waitForVisibility(By locator, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(WDController.getDriver(), timeout);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public static Boolean waitForInVisibility(By locator, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(WDController.getDriver(), timeout);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    public static WebElement waitForClickablility(WebElement element, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(WDController.getDriver(), timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static Boolean waitForClickablility( Duration timeout,WebElement element) {
        WebDriverWait wait = new WebDriverWait(WDController.getDriver(), timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(element)).isDisplayed();
    }
    public static WebElement waitForClickablility(By locator, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(WDController.getDriver(), timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public static void waitForPageToLoad(Duration timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            WebDriverWait wait = new WebDriverWait(WDController.getDriver(), timeOutInSeconds);
            wait.until(expectation);
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public static void executeJScommand(WebElement element, String command) {
        JavascriptExecutor jse = (JavascriptExecutor) WDController.getDriver();
        jse.executeScript(command, element);
    }

    public static void selectAnItemFromDropdown(WebElement item, String selectableItem){
        wait(5);
        Select select = new Select(item);
        for (int i =0;i<select.getOptions().size();i++){
            if(select.getOptions().get(i).getText().equalsIgnoreCase(selectableItem)){
                select.getOptions().get(i).click();
                break;
            }
        }

    }

    /**
     * Clicks on an element using JavaScript
     *
     * @param element
     */
    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) WDController.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) WDController.getDriver()).executeScript("arguments[0].click();", element);
    }



    /**
     * Performs double click action on an element
     *
     * @param element
     */
    public static void doubleClick(WebElement element) {
        new Actions(WDController.getDriver()).doubleClick(element).build().perform();
    }

    public static void selectByVisibleText(WebElement element, String text){
        Select objSelect =new Select(element);
        objSelect.selectByVisibleText(text);
    }

    public static void selectByIndex(WebElement element, int index){
        Select objSelect =new Select(element);
        objSelect.selectByIndex(index);
    }

    public static void selectByValue(WebElement element, String value) {
        Select objSelect = new Select(element);
        List<WebElement> elementCount = objSelect.getOptions();
        objSelect.selectByValue(value);
        System.out.println("number of elements: "+elementCount.size());
    }

    public static void sleep(int timeOut){
        try {
            Thread.sleep(timeOut);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void waitAndClickLocationText(WebElement element, String value){
        WDController.getDriver().findElement(By.xpath("//*[text()='"+value+"']")).click();
    }

    public static void navigateToUrl(String url){
        WDController.getDriver().get(url);
    }

}