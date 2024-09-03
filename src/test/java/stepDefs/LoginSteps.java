package stepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pages.LoginPage;
import utilities.ConfigurationReader;
import utilities.WDController;

import java.time.Duration;

public class LoginSteps {


    LoginPage lp;

    @Given("user navigates to login page")
    public void user_navigates_to_login_page() {

        WDController.getDriver().get(ConfigurationReader.getProperty("qa_url"));
    }

    @Given("user provides {string} and {string}")
    public void user_provides_and(String username, String password) {
        lp = new LoginPage();
        Assert.assertTrue(WDController.waitForClickablility(Duration.ofSeconds(5),lp.loginDropdown));
        WDController.waitAndClick(lp.loginDropdown);

        Assert.assertTrue(WDController.waitForClickablility(Duration.ofSeconds(5),lp.signInButton));
        WDController.waitAndClick(lp.signInButton);

        Assert.assertTrue(WDController.waitForClickablility(Duration.ofSeconds(5),lp.username));
        WDController.waitAndSendText(lp.username, username);


        Assert.assertTrue(WDController.waitForClickablility(Duration.ofSeconds(5),lp.password));
        WDController.waitAndSendText(lp.password, password);


    }

    @Then("user validates user login")
    public void user_validates_user_login() {

        Assert.assertTrue(WDController.waitForClickablility(Duration.ofSeconds(5),lp.loginButton));
        WDController.waitAndClick(lp.loginButton);
        Assert.assertTrue(WDController.waitForClickablility(Duration.ofSeconds(5),lp.navigatedPage));
    }
}
