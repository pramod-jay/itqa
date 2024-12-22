


package ui.StepDefinitions;


import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ui.BaseSteps.BaseSteps;

import java.time.Duration;


public class AddAdminUser extends BaseSteps {
    @Before
    public void setup() {
        wait =  new WebDriverWait(driver, Duration.ofSeconds(10));;
    }


    @When("I click the admin button")
    public void i_click_the_admin_button() {
        WebElement adminBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[1]"
        )));
        adminBtn.click();

    }
    @Then("I should see the user management screen")
    public void i_should_see_the_user_management_screen() {
        wait.until(ExpectedConditions.urlToBe("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
    }
    @When("I click the add button")
    public void i_click_the_add_button() {
        WebElement addUserBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[1]/button"
        )));
        addUserBtn.click();


    }
    @Then("I should see the Add User Form")
    public void i_should_see_the_add_user_form() {
        wait.until(ExpectedConditions.urlToBe("https://opensource-demo.orangehrmlive.com/web/index.php/admin/saveSystemUser"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://opensource-demo.orangehrmlive.com/web/index.php/admin/saveSystemUser");
    }
    @Then("I select a user role from the dropdown")
    public void i_select_a_user_role_from_the_dropdown() {
        WebElement dropDownBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/div/div/div[2]"
        )));
        dropDownBtn.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/div/div[2]"
        )));
        WebElement dropdownItem = driver.findElement(By.xpath(
                "//div[@class='oxd-select-option']/span[text()='Admin']"
        ));
        dropdownItem.click();
    }


    @And("I type {string} in the employee name field")
    public void i_type_in_the_employee_name_field(String employeeName) {
        WebElement employeeNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/div/div[2]/div/div/input"
        )));
        employeeNameField.sendKeys(employeeName);
    }


    @And("I select a status from the dropdown")
    public void i_select_a_status_from_the_dropdown() {
        WebElement dropDownBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[3]/div/div[2]"
        )));
        dropDownBtn.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[3]/div/div[2]"
        )));
        WebElement dropdownItem = driver.findElement(By.xpath(
                "//div[@class='oxd-select-option']/span[text()='Enabled']"
        ));
        dropdownItem.click();
    }

    @And("I enter {string} in the user name field")
    public void i_enter_in_the_user_name_field(String userName) {
        WebElement userNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/div/div[2]/div/div/input"
        )));
        userNameField.sendKeys(userName);
    }
    @And("I enter {string} in the password field")
    public void i_enter_in_the_password_field(String  password) {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/input"
        )));
        passwordField.sendKeys(password);
    }
    @And("I enter {string} in the confirm password field")
    public void i_enter_in_the_confirm_password_field(String confirmPassword) {
        WebElement confirmPasswordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/input"
        )));
        confirmPasswordField.sendKeys(confirmPassword);
    }
    @When("I click the Save button")
    public void i_click_the_save_button() {
        WebElement saveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/button[2]"
        )));
        saveButton.click();
    }
    @Then("I should see a password mismatch error with message {string}")
    public void i_should_see_a_password_mismatch_error_with_message(String errorMessage) {
        WebElement errorText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div[2]/div/span"
        )));
        Assert.assertEquals(errorText.getText(), errorMessage);
    }


}
