package ui.StepDefinitions;

import io.cucumber.java.After;
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
    @Given("I select the leave type")
    public void i_select_the_leave_type() {
        WebElement dropDownBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/div/div/div[2]")));
        dropDownBtn.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/div/div[2]"
        )));
        WebElement dropdownItem = driver.findElement(By.xpath(
                "//div[@class='oxd-select-option']/span[text()='CAN - FMLA']"
        ));
        dropdownItem.click();
    }
    @Given("I enter a valid from date")
    public void i_enter_a_valid_from_date() {
        WebElement fromCalendarBtn = driver.findElement(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/div[2]/div/div/i"
        ));
        fromCalendarBtn.click();
        Integer currentDate = getDate(LocalDate.now());
        WebElement dateElement = driver.findElement(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/div[2]/div/div[2]/div/div[3]/div["+currentDate+"]"
        ));
        dateElement.click();
    }
    @Given("I enter a valid to date")
    public void i_enter_a_valid_to_date() {
        WebElement toCalendarBtn = driver.findElement(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/div/div[1]/i"
        ));
        toCalendarBtn.click();
        Integer dayAfterTomorrow = getDate(LocalDate.now().plusDays(2));
        WebElement dateElement = driver.findElement(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div["+dayAfterTomorrow+"]"
        ));
        dateElement.click();
    }
    @Then("I should see the {string} selector")
    public void i_should_see_the_selector(String selector) {
        //Check label is displayed
        WebElement selectorLabel = driver.findElement(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div/div/div[1]/label"
        ));
        assert  selectorLabel.getText().equals(selector) : "Label text does not match";

        //Check menu is displayed
        WebElement selectorElement = driver.findElement(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/div/div/div/div[2]/div/div"
        ));
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
            assert  item.getText().equals(itemText[i]) : "Item does not match";
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
    @When("I click the Apply button")
    public void i_click_the_apply_button() {
       WebElement applyBtn = driver.findElement(By.xpath(
               "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[5]/button"
       ));
       applyBtn.click();
    }
    @Then("I should see a success message confirming the leave application with title {string} and message {string}")
    public void i_should_see_a_success_message_confirming_the_leave_application(String title, String message) {
        WebElement msgElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(
                "oxd-toaster_1"
        )));
        assert msgElement.isDisplayed() : "Message element is not displayed";

        WebElement titleElement = driver.findElement(By.xpath("//*[@id=\"oxd-toaster_1\"]/div/div[1]/div[2]/p[1]"));
        assert titleElement.getText().equals(title) : "Title does not match";

        WebElement messageElement = driver.findElement(By.xpath("//*[@id=\"oxd-toaster_1\"]/div/div[1]/div[2]/p[2]"));
        assert  messageElement.getText().equals(message) : "Message does not match";
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
}
