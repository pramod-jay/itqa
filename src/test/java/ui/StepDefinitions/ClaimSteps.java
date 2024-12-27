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



    //need to include the elements
//    @Then("I should see a message with title {string} and message {string} on event screen")
//    public void i_should_see_a_message_with_title_and_message_on_event_screen(String eventMsgTitle, String eventMsg) {
//        WebElement eventMsgTitleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
//                "//*[@id=\"oxd-toaster_1\"]/div/div[1]/div[2]/p[1]"
//        )));
//        assert eventMsgTitleElement.isDisplayed() : "Message element is not displayed on add event screen";
//
//        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
//                "//*[@id=\"oxd-toaster_1\"]/div/div[1]/div[2]/p[1]"
//        )));
//        Assert.assertEquals(titleElement.getText(), eventMsgTitle);
//
//        WebElement eventMsgElement = driver.findElement(By.xpath("//*[@id=\"oxd-toaster_1\"]/div/div[1]/div[2]/p[2]"));
//        Assert.assertEquals(eventMsgElement.getText(), eventMsg);
//    }
}
