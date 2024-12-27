package ui.StepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ui.BaseSteps.BaseSteps;
import java.time.Duration;

public class AddQualificationsSteps extends BaseSteps {

    @Before
    public void setup() {
        wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //Background to add qualification form
    @When("I click the My info button")
    public void i_click_the_my_info_button() {
        WebElement myInfoBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[6]"
        )));
        myInfoBtn.click();
    }
    @Then("I should see the personal info screen")
    public void i_should_see_the_personal_info_screen() {
        wait.until(ExpectedConditions.urlToBe("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewPersonalDetails/empNumber/7"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewPersonalDetails/empNumber/7");
    }
    @When("I click the Qualifications button")
    public void i_click_the_qualifications_button() {
        WebElement qualificationsBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[1]/div[2]/div[9]/a"
        )));
        qualificationsBtn.click();
    }
    @Then("I should see the Qualifications screen")
    public void i_should_see_the_qualifications_screen() {
        wait.until(ExpectedConditions.urlToBe("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewQualifications/empNumber/7"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewQualifications/empNumber/7");
    }

    @When("I click work experience add button")
    public void i_click_work_experience_add_button() {
        WebElement addWorkExperienceBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div[1]/div/button"
        )));
        addWorkExperienceBtn.click();
    }

    @Then("I should see the work experience adding section")
    public void i_should_see_the_work_experience_adding_section() {
        WebElement sectionTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div[1]/h6"
        )));
        assert sectionTitle.isDisplayed() : "Section is not opened";

        Assert.assertEquals(sectionTitle.getText(), "Add Work Experience");
    }

    //Testing successful qualification adding
    @Then("I enter {string} in the company field")
    public void i_enter_in_the_company_field(String companyName) {
        WebElement companyNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div[1]/form/div[1]/div/div[1]/div/div[2]/input"
        )));
        companyNameField.sendKeys(companyName);
    }

    @Then("I enter {string} in the job title field")
    public void i_enter_in_the_job_title_field(String jobTitle) {
        WebElement jobTitleField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div[1]/form/div[1]/div/div[2]/div/div[2]/input"
        )));
        jobTitleField.sendKeys(jobTitle);
    }

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

    @When("I click the Qualifications Save button")
    public void i_click_the_save_button() {
        WebElement saveBtn = driver.findElement(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div[1]/form/div[4]/button[2]"
        ));
        saveBtn.click();
    }

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

    @When("I click language add button")
    public void i_click_language_add_button() {
        WebElement addLanguageBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[5]/div[1]/div/button"
        )));
        addLanguageBtn.click();
    }

    @Then("I should see the language adding section")
    public void i_should_see_the_language_adding_section() {
        WebElement sectionTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[5]/div[1]/h6"
        )));
        assert sectionTitle.isDisplayed() : "Section is not opened";

        Assert.assertEquals(sectionTitle.getText(), "Add Language");
    }
    @Then("I select a language")
    public void i_select_a_language() {
        WebElement dropDownBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[5]/div[1]/form/div[1]/div/div[1]/div/div[2]/div/div/div[2]/i"
        )));
        dropDownBtn.click();

    }
    @Then("I select a fluency")
    public void i_select_a_fluency() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("I select a competency")
    public void i_select_a_competency() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("I click the language Save button")
    public void i_click_the_language_save_button() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


}
