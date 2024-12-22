package ui.StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ui.BaseSteps.BaseSteps;
import ui.utils.WebDriverUtil;
import io.qameta.allure.*;

import java.time.Duration;

@Epic("Login Module")
@Feature("User Login")
@Owner("Pramod Jayathilaka")
public class LoginSteps extends BaseSteps {
    @Severity(SeverityLevel.CRITICAL)
    @Description("Navigate to the login page of the OrangeHRM application.")
    @Step("Navigate to the login screen")
    @Given("I am on the login screen")
    public void i_am_on_the_login_screen() {
        driver = WebDriverUtil.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }
    @Severity(SeverityLevel.CRITICAL)
    @Description("Enter a valid username into the username field.")
    @Step("Enter a valid username: {userName}")
    @When("I enter a valid username {string}")
    public void i_enter_a_valid_username(String userName) {
        WebElement userNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        userNameElement.sendKeys(userName);
    }
    @Severity(SeverityLevel.CRITICAL)
    @Description("Enter a valid password into the password field.")
    @Step("Enter a valid password")
    @When("I enter a valid password {string}")
    public void i_enter_a_valid_password(String password) {
        WebElement passwordElement = driver.findElement(By.name("password"));
        passwordElement.sendKeys(password);
    }
    @Severity(SeverityLevel.NORMAL)
    @Description("Click the login button to submit the credentials.")
    @Step("Click the Login button")
    @When("I click the Login button")
    public void i_click_the_login_button() {
        WebElement loginBtn = driver.findElement(By.xpath(
                "//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button"
        ));
        loginBtn.click();
    }
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the dashboard is displayed upon successful login.")
    @Step("Verify the dashboard is displayed")
    @Then("I should see the dashboard")
    public void i_should_see_the_dashboard() {
        wait.until(ExpectedConditions.urlToBe("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
    }
}
