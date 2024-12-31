package ui.StepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ui.BaseSteps.BaseSteps;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import static org.testng.AssertJUnit.assertEquals;

public class AddCandidateSteps extends BaseSteps {
    @Severity(SeverityLevel.BLOCKER)
    @Description("Setup method to initialize WebDriverWait before each scenario.")
    @Step("Initialize WebDriverWait with a 10-second timeout")
    @Before
    public void setup() {
        wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Click the Recruitment button to navigate to the Recruitment screen.")
    @Step("Click the Recruitment button")
    @When("I click the Recruitment button")
    public void i_click_the_recruitment_button() {
        WebElement recruitmentBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[5]/a"
        )));
        recruitmentBtn.click();
    }


    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the Recruitment screen is displayed after clicking the Recruitment button.")
    @Step("Verify the Recruitment screen is displayed")
    @Then("I should see the Recruitment screen")
    public void i_should_see_the_recruitment_screen() {
        wait.until(ExpectedConditions.urlToBe("https://opensource-demo.orangehrmlive.com/web/index.php/recruitment/viewCandidates"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://opensource-demo.orangehrmlive.com/web/index.php/recruitment/viewCandidates");
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Navigate to the Add Candidate page.")
    @Step("Navigate to Add Candidate")
    @Given("I navigate to the add candidate")
    public void i_click_add_candidate_button() {
        WebElement addCandidateBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[1]/button"
        )));
        addCandidateBtn.click();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Add candidate details to the form.")
    @Step("Fill in the candidate details")
    @When("I add the candidate details:")
    public void i_add_the_candidate_details(DataTable dataTable) {
        // Extract candidate details from the DataTable
        List<List<String>> rows = dataTable.asLists(String.class);
        String firstName = rows.get(1).get(0);
        String middleName = rows.get(1).get(1);
        String lastName = rows.get(1).get(2);
        String email = rows.get(1).get(3);
        String contactNo = rows.get(1).get(4);
        String vacancy = rows.get(1).get(5);
        String resumeFileName = rows.get(1).get(6);
        String keywords = rows.get(1).get(7);
        String comment = rows.get(1).get(8);
        String applicationDate = rows.get(1).get(9);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Fill in candidate details
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div/div/div[2]/div[1]/div[2]/input")))
                .sendKeys(firstName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div/div/div[2]/div[2]/div[2]/input")))
                .sendKeys(middleName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div/div/div[2]/div[3]/div[2]/input")))
                .sendKeys(lastName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[1]/div/div[2]/input"))).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[1]/div/div[2]/input"))).sendKeys(contactNo);

        // Select job vacancy

        // Step 1: Click the dropdown to expand it
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".oxd-select-wrapper")));
        dropdown.click();

        // Step 2: Wait for all options to load
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".oxd-select-option")));

        // Step 3: Iterate through the options and match the desired option
        boolean optionFound = false;

        for (WebElement option : options) {
            String optionText = option.getText();
            if (optionText.equals(vacancy)) {
                option.click(); // Click the matching option
                optionFound = true;
                break;
            }
        }

        if (!optionFound) {
            throw new NoSuchElementException("Option '" + vacancy + "' not found in the dropdown");
        }

        // Locate the custom file upload element
        WebElement uploadDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'oxd-file-div--active')]")
        ));
        // Click the div to open the file dialog
        uploadDiv.click();

        // Locate the hidden <input> element and send the file path
        WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\resume.pdf"; // Adjust file path
        fileInput.sendKeys(filePath);

        // Set keywords and comments
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[5]/div/div[1]/div/div[2]/input"))).sendKeys(keywords);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[5]/div/div[1]/div/div[2]/input"))).sendKeys(comment);

        WebElement saveBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[8]/button[2]")));
        saveBtn.click();
    }



    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify the confirmation message displayed after saving the candidate.")
    @Step("Verify confirmation message")
    @Then("I should see a confirmation message {string}")
    public void i_should_see_a_confirmation_message(String expectedMessage) {
        WebElement toastMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='oxd-toaster_1']")));

        // Retrieve the actual message from the toast element and clean it
        String actualMessage = toastMessageElement.getText()
                .replace("×", "")       // Remove the '×' character
                .replaceAll("\\s+", " ") // Normalize multiple spaces to a single space
                .trim();                // Trim leading and trailing spaces

        // Verify the actual message matches the expected message
        assertEquals("Confirmation message mismatch!",expectedMessage, actualMessage);
    }
}
