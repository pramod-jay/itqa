package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.WebDriverUtil;

import java.time.Duration;

public class LoginSteps {
    WebDriver driver;
    WebDriverWait wait;

    @Given("I am on the log in page")
    public void i_am_on_the_log_in_page() {
        driver = WebDriverUtil.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(4));

        driver.get("https://parabank.parasoft.com/parabank/index.htm?ConnType=JDBC");
    }
    @When("I enter a valid username")
    public void i_enter_a_valid_username() {
        WebElement userName = driver.findElement(By.name("username"));
        userName.clear();
        userName.sendKeys("testUser123");
    }
    @And("I enter a valid password")
    public void i_enter_a_valid_password() {
        WebElement password = driver.findElement(By.name("password"));
        password.clear();
        password.sendKeys("Test@123");
    }

    @When("I enter an invalid username {string}")
    public void i_enter_a_invalid_username(String userName) {
        WebElement userNameField = driver.findElement(By.name("username"));
        userNameField.clear();
        userNameField.sendKeys(userName);
    }
    @And("I enter an invalid password {string}")
    public void i_enter_a_invalid_password(String password) {
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    @And("I click the Log in button")
    public void i_click_the_button() {
        WebElement loginBtn = driver.findElement(By.xpath("//*[@id=\"loginPanel\"]/form/div[3]/input"));
        loginBtn.click();
    }
    @Then("I should be visible Accounts Overview section")
    public void i_should_be_visible_section() {
        wait.until(ExpectedConditions.urlToBe("https://parabank.parasoft.com/parabank/overview.htm"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://parabank.parasoft.com/parabank/overview.htm");
    }

    @Then("I should be visible {string} error message")
    public void i_should_be_visible_error_message(String errorMessage) {
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"rightPanel\"]/p")));
        assert errorElement.isDisplayed() : "Expected error message to be displayed: " + errorMessage;
    }

    @After
    public void tearDown() {
        if(driver != null) {
            driver.quit();
        }
    }
}
