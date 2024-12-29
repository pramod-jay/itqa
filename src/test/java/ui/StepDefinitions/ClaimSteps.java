package ui.StepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.de.Wenn;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ui.BaseSteps.BaseSteps;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Epic("Claim Management")
@Feature("Claim Functionality")
@Owner("Nathali Fernando")
public class ClaimSteps extends BaseSteps {
    @Severity(SeverityLevel.BLOCKER)
    @Description("Setup method to initialize WebDriverWait before each scenario.")
    @Step("Initialize WebDriverWait with a 10-second timeout")
    @Before
    public void setup() {
        wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Click on the Claim button and navigate to the Claim screen.")
    @Step("Click the Claim button")
    @When("I click the Claim button")
    public void i_click_the_claim_button() {
        WebElement claimBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[11]/a"
        )));
        claimBtn.click();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the Claim screen is displayed.")
    @Step("Validate Claim screen navigation")
    @Then("I should see the Claim screen")
    public void i_should_see_the_claim_screen() {
        wait.until(ExpectedConditions.urlToBe("https://opensource-demo.orangehrmlive.com/web/index.php/claim/viewAssignClaim"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://opensource-demo.orangehrmlive.com/web/index.php/claim/viewAssignClaim");
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Open the Configuration dropdown menu.")
    @Step("Click Configuration dropdown")
    @When("I click Configuration dropdown")
    public void i_click_configuration_dropdown() {
        //click configuration button
        WebElement configDropDown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[1]/span"
        )));
        configDropDown.click();

        //Check dropdown is displayed
        WebElement dropDownMenuElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[1]/ul"
        )));
        assert  dropDownMenuElement.isDisplayed(): "Dropdown items are not available";

        //Check dropdown items
        String[] dropDownItem = {
                "//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[1]/ul/li[1]/a",
                "//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[1]/ul/li[2]/a"
        };

        String[] dropDownText = {
                "Events",
                "Expense Types"
        };

        for (int i=0; i<dropDownItem.length;i++){
            WebElement dDitem = driver.findElement(By.xpath(dropDownItem[i]));
            Assert.assertEquals(dDitem.getText(),dropDownText[i]);
        }

    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Select 'Events' from the Configuration dropdown.")
    @Step("Select Events from dropdown")
    @When("select Events")
    public void select_events() {
        WebElement eventsOption  = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[1]/ul/li[1]/a"
        )));
        eventsOption.click();
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the Events screen is displayed.")
    @Step("Validate Events screen navigation")
    @Then("I should see Events screen")
    public void i_should_see_events_screen() {
        wait.until(ExpectedConditions.urlToBe("https://opensource-demo.orangehrmlive.com/web/index.php/claim/viewEvents"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://opensource-demo.orangehrmlive.com/web/index.php/claim/viewEvents");

        WebElement eventHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/h5"
        )));
        Assert.assertEquals(eventHeader.getText(),"Events");
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Click the Add button on the Events screen.")
    @Step("Click Add button on Events screen")
    @Then("I click Add button on Events screen")
    public void i_click_add_button_on_events_screen() {
        WebElement addEventBtn  = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div[2]/div[1]/button"
        )));
        addEventBtn.click();

    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the Add Event screen is displayed.")
    @Step("Validate Add Event screen navigation")
    @Then("I should see Add Event screen")
    public void i_should_see_add_event_screen() {
        wait.until(ExpectedConditions.urlToBe("https://opensource-demo.orangehrmlive.com/web/index.php/claim/saveEvents"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://opensource-demo.orangehrmlive.com/web/index.php/claim/saveEvents");

        //optional
        WebElement addEventHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/h6"
        )));
        Assert.assertEquals(addEventHeader.getText(),"Add Event");
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Enter the specified Event Name.")
    @Step("Enter {eventName} as Event Name")
    @Then("I enter {string} for Event Name")
    public void i_enter_for_event_name(String eventName) {
        WebElement eventNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[1]/div/div[2]/input"
        )));
        eventNameElement.sendKeys(eventName);
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Enter the specified Event Description.")
    @Step("Enter {eventDescription} as Event Description")
    @Then("I enter {string} for Description")
    public void i_enter_for_description(String eventDescription) {
        WebElement eventDescriptionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div/div[2]/textarea"
        )));
        eventDescriptionElement.sendKeys(eventDescription);
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Toggle the active state for the event.")
    @Step("Press the toggle")
    @Then("I press the toggle")
    public void i_press_the_toggle() {
        WebElement toggleElement = driver.findElement(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[3]/div/div"
        ));
        assert toggleElement.isDisplayed() : "Active toggle element is not displayed.";
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Save the event after entering details.")
    @Step("Click Save button on Add Event screen")
    @Then("I click event Save button")
    public void i_click_event_save_button() {
        WebElement addEventSave = driver.findElement(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/button[2]"
        ));
        addEventSave.click();
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Check for 'Already exists' error message when event name duplicates.")
    @Step("Verify 'Already exists' error message")
    @Then("I should see a Already exists error message below Event Name field")
    public void i_should_see_a_already_exists_error_message_below_event_name_field() {
        WebElement eventErrorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[1]/div/span"
        )));
        Assert.assertEquals(eventErrorMsg.getText(), "Already exists");
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Clicks the Submit Claim button to navigate to the Claim Request creation page.")
    @Step("Click Submit Claim button")
    @When("I click Submit Claim button")
    public void i_click_submit_claim_button() {
        WebElement claimSubmitBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[2]"
        )));
        claimSubmitBtn.click();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Verifies that the Create Claim Request screen is displayed after clicking Submit Claim.")
    @Step("Verify Create Claim Request screen is visible")
    @Then("I should see Create Claim Request screen")
    public void i_should_see_create_claim_request_screen() {
        wait.until(ExpectedConditions.urlToBe("https://opensource-demo.orangehrmlive.com/web/index.php/claim/submitClaim"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://opensource-demo.orangehrmlive.com/web/index.php/claim/submitClaim");

        WebElement submitClaimHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/h6"
        )));
        Assert.assertEquals(submitClaimHeader.getText(),"Create Claim Request");
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Selects 'Travel Allowance' from the event dropdown.")
    @Step("Select Travel Allowance event")
    @Then("I select event Travel Allowance from dropdown")
    public void i_select_event_travel_allowance_from_dropdown() {

        WebElement eventSelection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/div/div"
        )));
        eventSelection.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/div/div"
        )));
        WebElement eventSelectionItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/div/div[2]/div[5]"
        )));

        eventSelectionItem.click();

    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Selects Sri Lankan Rupee from the currency dropdown.")
    @Step("Select Sri Lankan Rupee as currency")
    @Then("I select Sri Lankan rupee as currency from the given dropdown")
    public void i_select_sri_lankan_rupee_as_currency_from_the_given_dropdown() {
        WebElement currencySelection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/div/div[2]/div/div"
        )));
        currencySelection.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/div/div[2]/div/div[2]"
        )));
        WebElement currencySelectionItem = driver.findElement(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/div/div[2]/div/div[2]/div[132]/span"
        ));
        currencySelectionItem.click();
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Enters a remark for the claim request.")
    @Step("Enter claim remark")
    @Then("I should enter remark as {string}")
    public void i_should_enter_remark_as(String createClaimRemark) {
        WebElement createClaimRemarkElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div/div/div[2]/textarea"
        )));
        createClaimRemarkElement.sendKeys(createClaimRemark);
    }


    @Severity(SeverityLevel.NORMAL)
    @Description("Clicks the 'Create Claim Request' button to submit the claim.")
    @Step("Click Create Claim Request button")
    @Then("I click Claim request Create button")
    public void i_click_claim_request_create_button() {
        WebElement createClaimBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/button[2]"
        )));
        createClaimBtn.click();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Verifies that the submitted claim displays a reference ID.")
    @Step("Verify reference ID is displayed for submitted claim")
    @Then("I should see submitted claim with a reference Id")
    public void i_should_see_submitted_claim_with_a_reference_id() {
        WebElement refID = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div[1]/form/div[1]/div/div[1]/div/div[2]/input"
        )));

        String ref = refID.getDomProperty("value");
        Assert.assertNotEquals(ref,null);
        String id = ref.substring(ref.length() - 2);
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://opensource-demo.orangehrmlive.com/web/index.php/claim/submitClaim/id/"+id);
    }


    @Severity(SeverityLevel.CRITICAL)
    @Description("Checks for error messages if Event and Currency fields are not filled.")
    @Step("Verify error messages for Event and Currency fields")
    @Then("I should see {string} error messages below Event and Currency fields")
    public void i_should_see_error_messages_below_event_and_currency_fields(String requiredMsg) {
        //Events
        WebElement eventSelectionError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/span"
        )));
        assert eventSelectionError.isDisplayed() : "Leave type error message element is not displayed";
        Assert.assertEquals(eventSelectionError.getText(), requiredMsg);

        //Currency
        WebElement currencySelectionError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/div/span"
        )));
        assert  currencySelectionError.isDisplayed(): "Currency selection error message element is not displayed";
        Assert.assertEquals(currencySelectionError.getText(),requiredMsg);
    }
}
