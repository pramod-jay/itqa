package ui.StepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ui.Globals.BaseSteps;

import java.time.Duration;

public class AddQualificationsSteps extends BaseSteps {

    @Before
    public void setup() {
        wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
    }

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

}
