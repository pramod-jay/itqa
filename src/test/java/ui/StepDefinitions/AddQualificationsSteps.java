package ui.StepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
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

@Epic("Add Qualifications Module")
@Feature("Qualifications Adding Functionality")
@Owner("Buddima Dissanayake")
public class AddQualificationsSteps extends BaseSteps {

    @Step("Initialize WebDriverWait with a 10-second timeout")
    @Description("Setup method to initialize WebDriverWait before each scenario.")
    @Before
    public void setup() {
        wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //Background to add qualification form
    @Step("Click the My Info button")
    @Description("Clicks on the My Info button to navigate to the Personal Info screen")
    @When("I click the My info button")
    public void i_click_the_my_info_button() {
        WebElement myInfoBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[6]"
        )));
        myInfoBtn.click();
    }
    @Step("Verify Personal Info screen")
    @Description("Validates that the Personal Info screen is displayed")
    @Then("I should see the personal info screen")
    public void i_should_see_the_personal_info_screen() {
        wait.until(ExpectedConditions.urlToBe("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewPersonalDetails/empNumber/7"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewPersonalDetails/empNumber/7");
    }
    @Step("Click the Qualifications button")
    @Description("Clicks on the Qualifications button to navigate to the Qualifications screen")
    @When("I click the Qualifications button")
    public void i_click_the_qualifications_button() {
        WebElement qualificationsBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[1]/div[2]/div[9]/a"
        )));
        qualificationsBtn.click();
    }
    @Step("Verify Qualifications screen")
    @Description("Validates that the Qualifications screen is displayed")
    @Then("I should see the Qualifications screen")
    public void i_should_see_the_qualifications_screen() {
        wait.until(ExpectedConditions.urlToBe("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewQualifications/empNumber/7"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewQualifications/empNumber/7");
    }

    @Step("Click Add Work Experience button")
    @Description("Clicks on the Add Work Experience button to open the work experience form")
    @When("I click work experience add button")
    public void i_click_work_experience_add_button() {
        WebElement addWorkExperienceBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div[1]/div/button"
        )));
        addWorkExperienceBtn.click();
    }

    @Step("Verify Add Work Experience section")
    @Description("Validates that the work experience adding section is displayed")
    @Then("I should see the work experience adding section")
    public void i_should_see_the_work_experience_adding_section() {
        WebElement sectionTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div[1]/h6"
        )));
        assert sectionTitle.isDisplayed() : "Section is not opened";

        Assert.assertEquals(sectionTitle.getText(), "Add Work Experience");
    }

    //Testing successful qualification adding
    @Step("Enter {companyName}")
    @Description("Enters the provided company name in the company field")
    @Then("I enter {string} in the company field")
    public void i_enter_in_the_company_field(String companyName) {
        WebElement companyNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div[1]/form/div[1]/div/div[1]/div/div[2]/input"
        )));
        companyNameField.sendKeys(companyName);
    }

    @Step("Enter {jobTitle}")
    @Description("Enters the provided job title in the job title field")
    @Then("I enter {string} in the job title field")
    public void i_enter_in_the_job_title_field(String jobTitle) {
        WebElement jobTitleField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div[1]/form/div[1]/div/div[2]/div/div[2]/input"
        )));
        jobTitleField.sendKeys(jobTitle);
    }

    @Step("Click from date")
    @Description("Enters the from date by clicking on calender element")
    @And("I click date in the from date calender")
    public void i_click_date_in_the_from_date_calender() {
        WebElement fromCalendarBtn = driver.findElement(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div[1]/form/div[2]/div/div[1]/div/div[2]/div/div/i"
        ));
        fromCalendarBtn.click();

        for(int i=0;i<4;i++) {
            WebElement monthBackBtn = driver.findElement(By.xpath(
                    "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div[1]/form/div[2]/div/div[1]/div/div[2]/div/div[2]/div/div[1]/button[1]"
            ));
            monthBackBtn.click();
        }

        WebElement date = driver.findElement(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div[1]/form/div[2]/div/div[1]/div/div[2]/div/div[2]/div/div[3]/div[10]/div"
        ));
        date.click();

    }

    @Step("Click To date")
    @Description("Enters the To date by clicking on calender element")
    @And("I click {string} in the to date calender")
    public void i_click_date_in_the_to_date_calender(String datePosition) {
        WebElement toCalendarBtn = driver.findElement(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div[1]/form/div[2]/div/div[2]/div/div[2]/div/div/i"
        ));
        toCalendarBtn.click();

        if(datePosition.equals("afterDate")) {
            for (int i = 0; i < 2; i++) {
                WebElement monthBackBtn = driver.findElement(By.xpath(
                        "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div[1]/form/div[2]/div/div[2]/div/div[2]/div/div[2]/div/div[1]/button[1]"
                ));
                monthBackBtn.click();
            }
        } else if (datePosition.equals("beforeDate")) {
            for (int i = 0; i < 6; i++) {
                WebElement monthBackBtn = driver.findElement(By.xpath(
                        "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div[1]/form/div[2]/div/div[2]/div/div[2]/div/div[2]/div/div[1]/button[1]"
                ));
                monthBackBtn.click();
            }
        }

        WebElement date = driver.findElement(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div[1]/form/div[2]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div[10]/div"
        ));
        date.click();
    }

    @Step("Click Qualifications Save button")
    @Description("Clicks on the Qualifications Save button to save the work experience")
    @When("I click the Qualifications Save button")
    public void i_click_the_save_button() {
        WebElement saveBtn = driver.findElement(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div[1]/form/div[4]/button[2]"
        ));
        saveBtn.click();
    }

    @Step("Verify error messages")
    @Description("Verify error messages for required fields or invalid date input")
    @Then("I should see the {string} error below the required fields")
    public void i_should_see_the_error_below_the_required_fields(String error) {
        if(error.equals("Required")) {
            //Company name
            WebElement companyError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div[1]/form/div[1]/div/div[1]/div/span"
            )));
            assert companyError.isDisplayed() : "Company name error message is not displayed";
            Assert.assertEquals(companyError.getText(), error);

            //Job title
            WebElement jobTitleError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div[1]/form/div[1]/div/div[2]/div/span"
            )));
            assert jobTitleError.isDisplayed() : "Job Title error message is not displayed";
            Assert.assertEquals(jobTitleError.getText(), error);
        } else if (error.equals("To date should be after from date")) {
            //To date
            WebElement invalidToDateError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div[1]/form/div[2]/div/div[2]/div/span"
            )));
            assert invalidToDateError.isDisplayed() : "Invalid to date error message is not displayed";
            Assert.assertEquals(invalidToDateError.getText(), error);
        }
    }

    @Step("Click language add button")
    @Description("Clicks on the Language Add button to open the language adding form")
    @When("I click language add button")
    public void i_click_language_add_button() {
        WebElement addLanguageBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[5]/div[1]/div/button"
        )));
        addLanguageBtn.click();
    }

    @Step("Verify Add Language section")
    @Description("Validates that the language adding section is displayed")
    @Then("I should see the language adding section")
    public void i_should_see_the_language_adding_section() {
        WebElement sectionTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[5]/div[1]/h6"
        )));
        assert sectionTitle.isDisplayed() : "Section is not opened";

        Assert.assertEquals(sectionTitle.getText(), "Add Language");
    }
    @Step("Select a language")
    @Description("Select a language from the dropdown menu")
    @Then("I select a language")
    public void i_select_a_language() {
        WebElement dropDownBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[5]/div[1]/form/div[1]/div/div[1]/div/div[2]/div/div/div[2]/i"
        )));
        dropDownBtn.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[5]/div[1]/form/div[1]/div/div[1]/div/div[2]/div/div[2]/div[1]"
        )));
        WebElement dropdownItem = driver.findElement(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[5]/div[1]/form/div[1]/div/div[1]/div/div[2]/div/div[2]/div[4]"
        ));
        dropdownItem.click();

    }
    @Step("Select a fluency")
    @Description("Select a fluency from the dropdown menu")
    @Then("I select a fluency")
    public void i_select_a_fluency() {
        WebElement dropDownBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[5]/div[1]/form/div[1]/div/div[2]/div/div[2]/div/div/div[2]/i"
        )));
        dropDownBtn.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[5]/div[1]/form/div[1]/div/div[2]/div/div[2]/div/div[2]/div[1]"
        )));
        WebElement dropdownItem = driver.findElement(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[5]/div[1]/form/div[1]/div/div[2]/div/div[2]/div/div[2]/div[2]"
        ));
        dropdownItem.click();
    }
    @Step("Select a competency")
    @Description("Select a competency from the dropdown menu")
    @Then("I select a competency")
    public void i_select_a_competency() {
        WebElement dropDownBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[5]/div[1]/form/div[1]/div/div[3]/div/div[2]/div/div/div[2]/i"
        )));
        dropDownBtn.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[5]/div[1]/form/div[1]/div/div[3]/div/div[2]/div/div[2]/div[1]"
        )));
        WebElement dropdownItem = driver.findElement(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[5]/div[1]/form/div[1]/div/div[3]/div/div[2]/div/div[2]/div[4]"
        ));
        dropdownItem.click();
    }
    @Step("Click Language Save button")
    @Description("Clicks on the Language Save button to save the language")
    @When("I click the language Save button")
    public void i_click_the_language_save_button() {
        WebElement saveBtn = driver.findElement(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[5]/div[1]/form/div[3]/button[2]"
        ));
        saveBtn.click();
    }
}
