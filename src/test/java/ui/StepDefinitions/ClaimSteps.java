package ui.StepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.de.Wenn;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClaimSteps extends BaseSteps {
    @Severity(SeverityLevel.BLOCKER)
    @Description("Setup method to initialize WebDriverWait before each scenario.")
    @Step("Initialize WebDriverWait with a 10-second timeout")
    @Before
    public void setup() {
        wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @When("I click the Claim button")
    public void i_click_the_claim_button() {
        WebElement claimBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[11]/a"
        )));
        claimBtn.click();
    }

    @Then("I should see the Claim screen")
    public void i_should_see_the_claim_screen() {
        wait.until(ExpectedConditions.urlToBe("https://opensource-demo.orangehrmlive.com/web/index.php/claim/viewAssignClaim"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://opensource-demo.orangehrmlive.com/web/index.php/claim/viewAssignClaim");
    }

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

    @When("select Events")
    public void select_events() {
        WebElement eventsOption  = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[1]/ul/li[1]/a"
        )));
        eventsOption.click();
    }

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

    @Then("I click Add button on Events screen")
    public void i_click_add_button_on_events_screen() {
        WebElement addEventBtn  = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div[2]/div[1]/button"
        )));
        addEventBtn.click();

    }
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

    @Then("I enter {string} for Event Name")
    public void i_enter_for_event_name(String eventName) {
        WebElement eventNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[1]/div/div[2]/input"
        )));
        eventNameElement.sendKeys(eventName);
    }

    @Then("I enter {string} for Description")
    public void i_enter_for_description(String eventDescription) {
        WebElement eventDescriptionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div/div[2]/textarea"
        )));
        eventDescriptionElement.sendKeys(eventDescription);
    }

    @Then("I press the toggle")
    public void i_press_the_toggle() {
        WebElement toggleElement = driver.findElement(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[3]/div/div"
        ));
        assert toggleElement.isDisplayed() : "Active toggle element is not displayed.";
    }
    @Then("I click event Save button")
    public void i_click_event_save_button() {
        WebElement addEventSave = driver.findElement(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/button[2]"
        ));
        addEventSave.click();
    }

      @Then("I should see a Already exists error message below Event Name field")
    public void i_should_see_a_already_exists_error_message_below_event_name_field() {
        WebElement eventErrorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div[1]/div/span"
        )));
        Assert.assertEquals(eventErrorMsg.getText(), "Already exists");
    }

    @When("I click Submit Claim button")
    public void i_click_submit_claim_button() {
        WebElement claimSubmitBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[1]/header/div[2]/nav/ul/li[2]"
        )));
        claimSubmitBtn.click();
    }
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

    @Then("I should enter remark as {string}")
    public void i_should_enter_remark_as(String createClaimRemark) {
        WebElement createClaimRemarkElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div/div/div[2]/textarea"
        )));
        createClaimRemarkElement.sendKeys(createClaimRemark);
    }
    @Then("I click Claim request Create button")
    public void i_click_claim_request_create_button() {
        WebElement createClaimBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/button[2]"
        )));
        createClaimBtn.click();
    }
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
}
