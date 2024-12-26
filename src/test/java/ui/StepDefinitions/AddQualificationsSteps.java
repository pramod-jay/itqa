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

    @And("I click date in the to date calender")
    public void i_click_date_in_the_to_date_calender() {
        WebElement toCalendarBtn = driver.findElement(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div[1]/form/div[2]/div/div[2]/div/div[2]/div/div/i"
        ));
        toCalendarBtn.click();

        for(int i=0;i<2;i++) {
            WebElement monthBackBtn = driver.findElement(By.xpath(
                    "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/div/div[2]/div[2]/div[1]/form/div[2]/div/div[2]/div/div[2]/div/div[2]/div/div[1]/button[1]"
            ));
            monthBackBtn.click();
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
    }
    
}
