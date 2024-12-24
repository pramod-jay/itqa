


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
import java.util.List;


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
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/div/div"
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

        WebElement dropDown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/div/div[2]/div/div[2]"
        )));

        WebElement itemValue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/div/div[2]/div/div[2]/div/span"
        )));
        Assert.assertEquals(itemValue.getText(), employeeName);
        dropDown.click();
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
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[4]/div/div[2]/input"
        )));
        userNameField.sendKeys(userName);
    }
    @And("I enter {string} in the password field")
    public void i_enter_in_the_password_field(String  password) {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/div[2]/input"
        )));
        passwordField.sendKeys(password);
    }
    @And("I enter {string} in the confirm password field")
    public void i_enter_in_the_confirm_password_field(String confirmPassword) {
        WebElement confirmPasswordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/input"
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



//    Check Empty

    @Then("I leave the user role field empty")
    public void iLeaveTheUserRoleFieldEmpty() {
        WebElement dropDownBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/div/div"
        )));
        dropDownBtn.click();
        // Do not select any value and click outside to trigger validation
        driver.findElement(By.xpath("//*[@id='app']/div[1]/div[2]/div[1]")).click();
    }

    @And("I leave the status field empty")
    public void iLeaveTheStatusFieldEmpty() {
        WebElement dropDownBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[3]/div/div[2]"
        )));
        dropDownBtn.click();
        driver.findElement(By.xpath("//*[@id='app']/div[1]/div[2]/div[1]")).click();
    }

    @And("I leave the employee name field empty")
    public void iLeaveTheEmployeeNameFieldEmpty() {
        WebElement employeeNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/div/div[2]/div/div/input"
        )));
        employeeNameField.clear();
        driver.findElement(By.xpath("//*[@id='app']/div[1]/div[2]/div[1]")).click();
    }

    @And("I leave the user name field empty")
    public void iLeaveTheUserNameFieldEmpty() {
        WebElement userNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[4]/div/div[2]/input"
        )));
        userNameField.clear();
        driver.findElement(By.xpath("//*[@id='app']/div[1]/div[2]/div[1]")).click();
    }

    @And("I leave the password field empty")
    public void iLeaveThePasswordFieldEmpty() {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/div[2]/input"
        )));
        passwordField.clear();
        driver.findElement(By.xpath("//*[@id='app']/div[1]/div[2]/div[1]")).click();
    }

    @And("I leave the confirm password field empty")
    public void iLeaveTheConfirmPasswordFieldEmpty() {
        WebElement confirmPasswordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/input"
        )));
        confirmPasswordField.clear();
        driver.findElement(By.xpath("//*[@id='app']/div[1]/div[2]/div[1]")).click();
    }

    @Then("I should see validation errors for all fields with the message {string}")
    public void iShouldSeeValidationErrorsForAllFieldsWithTheMessage(String message) {
        List<WebElement> errorMessages = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(
                "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message']"
        )));

        boolean passwordMismatchFound = false;

        for (WebElement errorMsg : errorMessages) {
            String actualMessage = errorMsg.getText();

            if (actualMessage.equals("Passwords do not match")) {
                passwordMismatchFound = true;
            } else {
                Assert.assertEquals(actualMessage, message, "Validation error did not match for all fields.");
            }
        }

        // Ensure at least one password error exists
        Assert.assertTrue(passwordMismatchFound, "Expected 'Passwords do not match' but it was not found.");

        // Optional: Ensure the total number of errors match (adjust if password validation is separate)
        Assert.assertEquals(errorMessages.size(), 6, "Number of validation errors is incorrect.");
    }
}
