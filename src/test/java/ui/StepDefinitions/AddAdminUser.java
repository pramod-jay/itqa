


package ui.StepDefinitions;


import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.cucumber.java.en.And;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ui.BaseSteps.BaseSteps;

import java.time.Duration;
import java.util.List;
import static org.testng.AssertJUnit.assertEquals;


public class AddAdminUser extends BaseSteps {
    @Before
    public void setup() {
        wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
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



//  Error message displayed when passwords do not match

    @Then("I should see a password mismatch error with message {string}")
    public void iShouldSeeAPasswordMismatchErrorWithMessage(String expectedMessage) {
        // Locate the error message using the provided XPath
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/span")
        ));

        String actualMessage = errorMessage.getText();
        assertEquals(expectedMessage, actualMessage);
    }




//    Validation error for weak password

    @Then("I should see a password strength error with message {string}")
    public void i_should_see_a_password_strength_error_with_message(String expectedMessage) {

            // Locate the error message using a relative XPath based on class
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[contains(@class, 'oxd-input-field-error-message') and text()='" + expectedMessage + "']")
            ));

            String actualMessage = errorMessage.getText();
            assertEquals(expectedMessage, actualMessage);

    }



//    Validation error when required fields are left empty

    @Then("I leave the user role field empty")
    public void iLeaveTheUserRoleFieldEmpty() {
        WebElement userRoleField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/div/div/input"
        )));
        userRoleField.sendKeys("");
    }

    @And("I leave the status field empty")
    public void iLeaveTheStatusFieldEmpty() {
        WebElement statusField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[3]/div/div[2]/input"
        )));
        statusField.sendKeys("");
    }

    @And("I leave the employee name field empty")
    public void iLeaveTheEmployeeNameFieldEmpty() {
        WebElement employeeNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/div/div[2]/div/div/input"
        )));
        employeeNameField.sendKeys("");
    }

    @And("I leave the user name field empty")
    public void iLeaveTheUserNameFieldEmpty() {
        WebElement userNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[4]/div/div[2]/input"
        )));
        userNameField.sendKeys("");
    }

    @And("I leave the password field empty")
    public void iLeaveThePasswordFieldEmpty() {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/div[2]/input"
        )));
        passwordField.sendKeys("");
    }

    @And("I leave the confirm password field empty")
    public void iLeaveTheConfirmPasswordFieldEmpty() {
        WebElement confirmPasswordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/input"
        )));
        confirmPasswordField.sendKeys("");
    }


    @Then("I should see {string} validation errors for all general fields")
    public void iShouldSeeValidationErrorsForAllGeneralFields(String validationMessage) {
        List<WebElement> validationErrors = driver.findElements(By.xpath("//span[@class='oxd-text oxd-text--span oxd-input-field-error-message']"));
        for (WebElement error : validationErrors) {
            Assert.assertEquals(error.getText(), validationMessage);
        }
    }

    @And("I should see {string} for the confirm password field")
    public void iShouldSeeForTheConfirmPasswordField(String validationMessage) {
        WebElement confirmPasswordError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/span"
        )));
        Assert.assertEquals(confirmPasswordError.getText(), validationMessage);
    }
}
