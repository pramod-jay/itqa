package ui.StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ui.Globals.BaseSteps;
import ui.utils.WebDriverUtil;

import java.time.Duration;

public class LoginSteps extends BaseSteps {
    @Given("I am on the login screen")
    public void i_am_on_the_login_screen() {
        driver = WebDriverUtil.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }
    @When("I enter a valid username {string}")
    public void i_enter_a_valid_username(String userName) {
        WebElement userNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        userNameElement.sendKeys(userName);
    }
    @When("I enter a valid password {string}")
    public void i_enter_a_valid_password(String password) {
        WebElement passwordElement = driver.findElement(By.name("password"));
        passwordElement.sendKeys(password);
    }
    @When("I click the Login button")
    public void i_click_the_login_button() {
        WebElement loginBtn = driver.findElement(By.xpath(
                "//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button"
        ));
        loginBtn.click();
    }
    @Then("I should see the dashboard")
    public void i_should_see_the_dashboard() {
        wait.until(ExpectedConditions.urlToBe("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
    }
}
