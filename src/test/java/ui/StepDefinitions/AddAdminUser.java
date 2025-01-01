


package ui.StepDefinitions;


import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.cucumber.java.en.And;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.openqa.selenium.By;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ui.BaseSteps.BaseSteps;

import java.time.Duration;
import java.util.List;
import static org.testng.AssertJUnit.assertEquals;


import io.qameta.allure.*;


@Epic("Admin Management")
@Feature("Add Admin User")
@Owner("Lasantha Pradeep")
public class AddAdminUser extends BaseSteps {
    @Before
    @Step("Setup WebDriver and wait configurations")
    public void setup() {
        wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    @Severity(SeverityLevel.CRITICAL)
    @Description("Click on the admin button to navigate to admin management page.")
    @Story("Navigate to Admin Section")
    @When("I click the admin button")
    public void i_click_the_admin_button() {
        WebElement adminBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[1]"
        )));
        adminBtn.click();

    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify user management screen appears after clicking admin button.")
    @Story("Admin Navigation")
    @Then("I should see the user management screen")
    public void i_should_see_the_user_management_screen() {
        wait.until(ExpectedConditions.urlToBe("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
    }
    @Severity(SeverityLevel.NORMAL)
    @Description("Click the add button to open the add user form.")
    @Story("Add New Admin")
    @When("I click the add button")
    public void i_click_the_add_button() {
        WebElement addUserBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[1]/button"
        )));
        addUserBtn.click();


    }
    @Severity(SeverityLevel.NORMAL)
    @Description("Ensure the add user form is displayed after clicking the add button.")
    @Story("Add New Admin")
    @Then("I should see the Add User Form")
    public void i_should_see_the_add_user_form() {
        wait.until(ExpectedConditions.urlToBe("https://opensource-demo.orangehrmlive.com/web/index.php/admin/saveSystemUser"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://opensource-demo.orangehrmlive.com/web/index.php/admin/saveSystemUser");
    }

    @Severity(SeverityLevel.MINOR)
    @Description("Select a user role from the dropdown menu.")
    @Story("Fill User Form")
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


    @Severity(SeverityLevel.NORMAL)
    @Description("Enter employee name and validate selection.")
    @Story("Fill User Form")
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


    @Severity(SeverityLevel.MINOR)
    @Description("Select status from the dropdown menu.")
    @Story("Fill User Form")
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

    @Step("Enter {userName} in the user name field")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Fills in the username field during user registration")
    @And("I enter {string} in the user name field")
    public void i_enter_in_the_user_name_field(String userName) {
        WebElement userNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[4]/div/div[2]/input"
        )));
        userNameField.sendKeys(userName);
    }
    @Step("Enter {password} in the password field")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Fills in the password field during user registration")
    @And("I enter {string} in the password field")
    public void i_enter_in_the_password_field(String  password) {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/div[2]/input"
        )));
        passwordField.sendKeys(password);
    }
    @Step("Enter {confirmPassword} in the confirm password field")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Fills in the confirm password field during user registration")
    @And("I enter {string} in the confirm password field")
    public void i_enter_in_the_confirm_password_field(String confirmPassword) {
        WebElement confirmPasswordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/input"
        )));
        confirmPasswordField.sendKeys(confirmPassword);
    }
    @Step("Click the Save button")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Clicks the save button to submit the form")
    @When("I click the Save button")
    public void i_click_the_save_button() {
        WebElement saveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/button[2]"
        )));
        saveButton.click();
    }



//  Error message displayed when passwords do not match

    @Step("Validate password mismatch error with message {expectedMessage}")
    @Severity(SeverityLevel.NORMAL)
    @Description("Validates if the correct password mismatch error is displayed")
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

    @Step("Validate password strength error with message {expectedMessage}")
    @Severity(SeverityLevel.NORMAL)
    @Description("Validates if the correct password strength error is displayed")
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

    @Step("Leave the user role field empty")
    @Severity(SeverityLevel.MINOR)
    @Description("Leaves the user role field empty for validation check")
    @Then("I leave the user role field empty")
    public void iLeaveTheUserRoleDropdownEmpty() {
        WebElement dropDownBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/div/div"
        )));
        dropDownBtn.click();

        // Close dropdown by clicking outside or pressing escape
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ESCAPE).perform();  // Simulate pressing escape to close the dropdown
    }

    @Step("Leave the status field empty")
    @Severity(SeverityLevel.MINOR)
    @Description("Leaves the status field empty for validation check")
    @And("I leave the status field empty")
    public void iLeaveTheStatusDropdownEmpty() {
        WebElement dropDownBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[3]/div/div[2]"
        )));
        dropDownBtn.click();

        // Close dropdown without selecting any item
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ESCAPE).perform();
    }

    @Step("Leave the employee name field empty")
    @Severity(SeverityLevel.NORMAL)
    @Description("Clears the employee name input field")
    @And("I leave the employee name field empty")
    public void iLeaveTheEmployeeNameFieldEmpty() {
        WebElement employeeNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/div/div[2]/div/div/input"
        )));
        employeeNameField.sendKeys("");
    }

    @Step("Leave the user name field empty")
    @Severity(SeverityLevel.NORMAL)
    @Description("Clears the user name input field")
    @And("I leave the user name field empty")
    public void iLeaveTheUserNameFieldEmpty() {
        WebElement userNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[4]/div/div[2]/input"
        )));
        userNameField.sendKeys("");
    }

    @Step("Leave the password field empty")
    @Severity(SeverityLevel.NORMAL)
    @Description("Clears the password input field")
    @And("I leave the password field empty")
    public void iLeaveThePasswordFieldEmpty() {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/div[2]/input"
        )));
        passwordField.sendKeys("");
    }

    @Step("Leave the confirm password field empty")
    @Severity(SeverityLevel.NORMAL)
    @Description("Clears the confirm password input field")
    @And("I leave the confirm password field empty")
    public void iLeaveTheConfirmPasswordFieldEmpty() {
        WebElement confirmPasswordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/input"
        )));
        confirmPasswordField.sendKeys("");
    }



    @Step("Verify validation errors for all fields")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checks for the presence of validation error messages in the form")
    @Then("I should see {string} validation errors for all general fields")
    public void iShouldSeeValidationErrorsForAllGeneralFields(String validationMessage) {
        List<WebElement> validationErrors = driver.findElements(By.xpath("//span[@class='oxd-text oxd-text--span oxd-input-field-error-message']"));
        for (WebElement error : validationErrors) {
            Assert.assertEquals(error.getText(), validationMessage);
        }
    }

    @Step("Verify confirm password field validation error")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Checks the validation message for the confirm password field")
    @And("I should see {string} for the confirm password field")
    public void iShouldSeeForTheConfirmPasswordField(String validationMessage) {
        WebElement confirmPasswordError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/span"
        )));
        Assert.assertEquals(confirmPasswordError.getText(), validationMessage);
    }

}
