package StepDefinitions;

import io.cucumber.java.After;
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
    WebDriver driver = WebDriverUtil.getDriver();

    @Given("I am on the log in page")
    public void i_am_on_the_log_in_page() {
        driver.get("https://parabank.parasoft.com/parabank/index.htm?ConnType=JDBC");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }
    @When("I enter a valid username")
    public void i_enter_a_valid_username() {
        WebElement userName = driver.findElement(By.name("username"));
        userName.clear();
        userName.sendKeys("testUser123");
    }
    @When("I enter a valid password")
    public void i_enter_a_valid_password() {
        WebElement password = driver.findElement(By.name("password"));
        password.clear();
        password.sendKeys("Test@123");
    }
    @When("I click the {string} button")
    public void i_click_the_button(String string) {
        WebElement loginBtn = driver.findElement(By.xpath("//*[@id=\"loginPanel\"]/form/div[3]/input"));
        loginBtn.click();
    }
    @Then("I should be visible {string} section")
    public void i_should_be_visible_section(String string) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.urlToBe("https://parabank.parasoft.com/parabank/overview.htm"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://parabank.parasoft.com/parabank/overview.htm");
    }

    @After
    public void tearDown() {
        if(driver != null) {
            driver.quit();
        }
    }
}
