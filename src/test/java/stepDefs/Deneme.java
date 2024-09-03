package stepDefs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import utilities.ConfigurationReader;
import utilities.WDController;

public class Deneme {

    public static void main(String[] args) {
        WDController.getDriver().get(ConfigurationReader.getProperty("qa_url"));

        LoginPage lp = new LoginPage();

        WDController.waitAndClick( lp.loginDropdown , 10);






    }





}
