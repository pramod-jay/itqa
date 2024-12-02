package StepDefinitions;

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
    WebDriver driver = WebDriverUtil.getDriver();
    @Given("user is on login page")
    public void user_is_on_login_page() {
        driver.get("https://online.uom.lk/login/index.php");
        System.out.println("Inside Step - user is on login page");
    }
    @When("user enters username and password")
    public void user_enters_username_and_password() {
        WebElement userName = driver.findElement(By.id("username"));
        userName.sendKeys("jayathilakaddpd.20");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("dilshanPJ470!");

        System.out.println("Inside Step - user enters username and password");
    }
    @And("clicks on login button")
    public void clicks_on_login_button() {
        WebElement loginBtn = driver.findElement(By.id("loginbtn"));
        loginBtn.click();
        System.out.println("Inside Step - clicks on login button");
    }
    @Then("user is navigated to the home page")
    public void user_is_navigated_to_the_home_page() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://online.uom.lk/my/"));

        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL, "https://online.uom.lk/my/");
        System.out.println("Inside Step - user is navigated to the home page");
    }
}
