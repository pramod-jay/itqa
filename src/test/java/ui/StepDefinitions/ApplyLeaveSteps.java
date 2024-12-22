package ui.StepDefinitions;

import io.cucumber.java.After;
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
import ui.utils.WebDriverUtil;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;

public class ApplyLeaveSteps {
    WebDriver driver;
    WebDriverWait wait;

    @Given("I am on the login screen")
    public void i_am_on_the_login_screen() {
        driver = WebDriverUtil.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }
    @When("I enter a valid username {string}")
    public void i_enter_a_valid_username(String userName) {
        WebElement userNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        userNameElement.sendKeys(userName);
    }
    @When("I enter a valid password {string}")
    public void i_enter_a_valid_password(String password) {
        WebElement passwordElement = driver.findElement(By.name("password"));
        passwordElement.sendKeys(password);
    }
    @When("I click the Login button")
    public void i_click_the_login_button() {
        WebElement loginBtn = driver.findElement(By.xpath(
                "//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button"
        ));
        loginBtn.click();
    }
    @Then("I should see the dashboard")
    public void i_should_see_the_dashboard() {
        wait.until(ExpectedConditions.urlToBe("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
    }
    @When("I click the Apply Leave button")
    public void i_click_the_apply_leave_button() {
        WebElement applyLeaveBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[3]/div/div[2]/div/div[4]/button"
        )));
        applyLeaveBtn.click();
    }
    @Then("I should see the Apply Leave screen")
    public void i_should_see_the_apply_leave_screen() {
        wait.until(ExpectedConditions.urlToBe("https://opensource-demo.orangehrmlive.com/web/index.php/leave/applyLeave"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://opensource-demo.orangehrmlive.com/web/index.php/leave/applyLeave");
    }
    @Then("I select the leave type")
    public void i_select_the_leave_type() {
        WebElement dropDownBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/div/div/div[2]"
        )));
        dropDownBtn.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/div/div[2]"
        )));
        WebElement dropdownItem = driver.findElement(By.xpath(
                "//div[@class='oxd-select-option']/span[text()='CAN - FMLA']"
        ));
        dropdownItem.click();
    }
    @And("I enter a {string} as the from date")
    public void i_enter_a_from_date(String type) {
        WebElement fromCalendarBtn = driver.findElement(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/div[2]/div/div/i"
        ));
        fromCalendarBtn.click();

        Integer date = switch (type) {
            case "today" -> getDate(LocalDate.now());
            case "weekend" -> getWeekend();
            case "monday" -> getWeekday();
            default -> 1;
        };

        WebElement dateElement = driver.findElement(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/div[2]/div/div[2]/div/div[3]/div["+date+"]"
        ));
        dateElement.click();
    }
    @And("I enter a {string} as the to date")
    public void i_enter_a_to_date(String type) {
        WebElement toCalendarBtn = driver.findElement(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/div/div[1]/i"
        ));
        toCalendarBtn.click();
        Integer date = switch (type) {
            case "day_after_tomorrow" -> getDate(LocalDate.now().plusDays(2));
            case "weekend" -> getWeekend();
            case "monday" -> getWeekday();
            default -> 1;
        };
        Integer dayAfterTomorrow = getDate(LocalDate.now().plusDays(2));
        WebElement dateElement = driver.findElement(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div["+date+"]"
        ));
        dateElement.click();
    }
    @Then("I should see the Partial Days selector")
    public void i_should_see_the_partial_days_selector() {
        //Check label is displayed
        WebElement selectorLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div/div/div[1]/label"
        )));

        Assert.assertEquals(selectorLabel.getText(), "Partial Days");

        //Check menu is displayed
        WebElement selectorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[1]/div/div[2]/div/div"
        )));
        selectorElement.click();
        WebElement menuElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div/div/div[2]/div/div[2]"
        )));
        assert menuElement.isDisplayed() : "Menu element is not displayed";

        //Check menu item
        String[] menuItem = {
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div/div/div[2]/div/div[2]/div[1]",
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div/div/div[2]/div/div[2]/div[2]/span",
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div/div/div[2]/div/div[2]/div[3]/span",
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div/div/div[2]/div/div[2]/div[4]/span",
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div/div/div[2]/div/div[2]/div[5]/span"
        };

        String[] itemText = {
                "-- Select --",
                "All Days",
                "Start Day Only",
                "End Day Only",
                "Start and End Day"
        };

        for(int i=0; i<menuItem.length; i++) {
            WebElement item = driver.findElement(By.xpath(menuItem[i]));
            Assert.assertEquals(item.getText(), itemText[i]);
        }
        //Close selector
        selectorElement.click();
    }
    @When("I enter comments as {string}")
    public void i_enter_comments(String comment) {
        WebElement commentElement = driver.findElement(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[4]/div/div/div/div[2]/textarea"
        ));
        commentElement.sendKeys(comment);
    }
    @Then("I click the Apply button")
    public void i_click_the_apply_button() {
        WebElement applyBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//button[contains(@class, 'oxd-button') and contains(@class, 'oxd-button--medium') and contains(@class, 'oxd-button--secondary') and contains(@class, 'orangehrm-left-space')]"
        )));
       applyBtn.click();
    }
    @Then("I should see a message with title {string} and message {string}")
    public void i_should_see_a_message_title_and_message(String title, String message) {
        WebElement msgElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(
                "oxd-toaster_1"
        )));
        assert msgElement.isDisplayed() : "Message element is not displayed";

        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"oxd-toaster_1\"]/div/div[1]/div[2]/p[1]"
        )));
        Assert.assertEquals(titleElement.getText(), title);

        WebElement messageElement = driver.findElement(By.xpath("//*[@id=\"oxd-toaster_1\"]/div/div[1]/div[2]/p[2]"));
        Assert.assertEquals(messageElement.getText(), message);
    }

    @Then("I should see a {string} error message below all the required fields")
    public void i_should_see_a_error_message_below_all_the_required_fields(String message) {
        //Leave type
        WebElement leaveTypeError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/span"
        )));
        assert leaveTypeError.isDisplayed() : "Leave type error message element is not displayed";
        Assert.assertEquals(leaveTypeError.getText(), message);

        //From date
        WebElement fromDateError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/span"
        )));
        assert fromDateError.isDisplayed() : "From date error message element is not displayed";
        Assert.assertEquals(fromDateError.getText(), message);

        //To date
        WebElement toDateError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/span"
        )));
        assert toDateError.isDisplayed() : "To date error message element is not displayed";
        Assert.assertEquals(toDateError.getText(), message);
    }
    @Then("I should see the Duration selector")
    public void i_should_see_the_duration_selector() {
        //Check label is displayed
        WebElement selectorLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[1]/div/div[1]/label"
        )));
        Assert.assertEquals(selectorLabel.getText(), "Duration");

        //Check menu is displayed
        WebElement dropDownBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[1]/div/div[2]/div/div"
        )));
        dropDownBtn.click();

        WebElement selectorMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div/div/div[2]/div/div[2]"
        )));
        assert selectorMenu.isDisplayed() : "Selector menu is not displayed";

        //Check menu items
        String[] menuItems = {
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div/div/div[2]/div/div[2]/div[1]/span",
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div/div/div[2]/div/div[2]/div[2]/span",
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div/div/div[2]/div/div[2]/div[3]/span",
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div/div/div[2]/div/div[2]/div[4]/span"
        };

        String[] itemTexts = {
                "Full Day",
                "Half Day - Morning",
                "Half Day - Afternoon",
                "Specify Time"
        };

        for(int i =0; i<menuItems.length; i++) {
            WebElement menuItem = driver.findElement(By.xpath(menuItems[i]));
            Assert.assertEquals(menuItem.getText(), itemTexts[i]);
        }
    }
    @Then("I should see the Duration")
    public void i_should_see_the_duration() {
        WebElement durationLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[4]/div/div[1]/label"
        )));
        Assert.assertEquals(durationLabel.getText(), "Duration");

        //Duration should be 8.00 hours
        WebElement duration = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[4]/div/div[2]/p"
        )));
        Assert.assertEquals(duration.getText(), "8.00");
    }
    @When("I select the specify time from the menu")
    public void i_select_the_specify_time_from_the_menu() {
        WebElement specifyTimeBtn  = driver.findElement(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div/div/div[2]/div/div[2]/div[4]"
        ));
        specifyTimeBtn.click();
    }
    @Then("I should see the From time selector")
    public void i_should_see_the_from_time_selector() {
        WebElement selectorLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[2]/div/div[1]/label"
        )));
        Assert.assertEquals(selectorLabel.getText(), "From");

        WebElement selectorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[2]/div/div[2]/div"
        )));
        selectorElement.click();

        WebElement timeSelector = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[2]/div/div[2]/div/div[2]"
        )));
        assert timeSelector.isDisplayed() : "Time selector is not displayed";
    }
    @Then("I set From time as 10.00AM")
    public void i_set_from_time_as_00am() {
        WebElement hourIncrementBtn = driver.findElement(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[2]/div/div[2]/div/div[2]/div[1]/i[1]"
        ));

        //Increment hours to 10
        while(true) {
            hourIncrementBtn.click();
            WebElement hourValue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[2]/div/div[2]/div/div[2]/div[1]/input"
            )));

            String value = hourValue.getDomProperty("value");
            assert value != null;
            if(value.equals("10")) {
                break;
            }
        }

        //Set minutes value as 00
        WebElement minuteValue = driver.findElement(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[2]/div/div[2]/div/div[2]/div[3]/input"
        ));
        minuteValue.sendKeys("00");

        //Set meridian as AM
        WebElement AMBtn = driver.findElement(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[2]/div/div[2]/div/div[2]/div[4]/div[1]"
        ));
        AMBtn.click();
    }
    @And("I should see the To time selector")
    public void i_should_see_the_to_time_selector() {
        WebElement selectorLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[3]/div/div[1]/label"
        )));
        Assert.assertEquals(selectorLabel.getText(), "To");

        WebElement selectorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[3]/div/div[2]/div/div"
        )));
        selectorElement.click();

        WebElement timeSelector = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[3]/div/div[2]/div/div[2]"
        )));
        assert timeSelector.isDisplayed() : "Time selector is not displayed";
    }
    @Then("I set To time as 2.00PM")
    public void i_set_to_time_as_00pm() {
        WebElement hourDecrementBtn = driver.findElement(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[3]/div/div[2]/div/div[2]/div[1]/i[2]"
        ));

        //Decrement hours to 02
        while (true) {
            hourDecrementBtn.click();
            WebElement hourValue = driver.findElement(By.xpath(
                    "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[3]/div/div[2]/div/div[2]/div[1]/input"
            ));
            String value = hourValue.getDomProperty("value");
            assert value != null;
            if(value.equals("02")) {
                break;
            }
        }

        //Set minutes value as 00
        WebElement minutesValue = driver.findElement(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[3]/div/div[2]/div/div[2]/div[3]/input"
        ));
        minutesValue.sendKeys("00");

        //Set meridian as PM
        WebElement PMBtn = driver.findElement(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[3]/div/div[2]/div/div[2]/div[4]/div[2]"
        ));
        PMBtn.click();
    }
    @And("I should see the Duration calculation")
    public void i_should_see_the_calculation() {
        WebElement duration = driver.findElement(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div[4]/div/div[2]/p"
        ));
        Assert.assertEquals(duration.getText(), "4.00");
    }

    @After
    public void tearDown() {
        if(driver != null) {
            driver.quit();
        }
    }

    private Integer getDate(LocalDate date) {
        return date.getDayOfMonth();
    }

    private Integer getWeekend() {
        LocalDate nextSunday = LocalDate.now().with(DayOfWeek.SUNDAY);
        if(!LocalDate.now().getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            nextSunday = nextSunday.with(DayOfWeek.SUNDAY).plusWeeks(1);
        }
        return getDate(nextSunday);
    }

    private Integer getWeekday() {
        LocalDate nextMonday = LocalDate.now().with(DayOfWeek.TUESDAY);
        if(!LocalDate.now().getDayOfWeek().equals(DayOfWeek.TUESDAY)) {
            nextMonday = nextMonday.with(DayOfWeek.TUESDAY).plusWeeks(1);
        }
        return getDate(nextMonday);
    }
}