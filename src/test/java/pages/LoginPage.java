package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WDController;

public class LoginPage extends CommonPageElements{

    public LoginPage(){
        super();
        PageFactory.initElements(WDController.getDriver(), this);

    }

    @FindBy(xpath = "//*[text()='Sign in']")
    public WebElement signInButton;
    @FindBy(id = "username")
    public WebElement username;
    @FindBy( id = "password")
    public WebElement password;
    @FindBy(xpath = "//*[@class='btn btn-primary']")
    public WebElement loginButton;


    @FindBy(xpath = "//*[contains(text(),'My Opera')]")
    public WebElement navigatedPage;


}
