package ui.StepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AddQualificationsSteps {

    WebDriver driver;
    WebDriverWait wait;

    @When("I click the My info button")
    public void i_click_the_my_info_button() {
        WebElement myInfoBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[6]"
        )));
        myInfoBtn.click();
    }
    @Then("I should see the personal info screen")
    public void i_should_see_the_personal_info_screen() {
        wait.until(ExpectedConditions.urlToBe("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewQualifications/empNumber/7"));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewQualifications/empNumber/7");
    }
    @When("I click the Qualifications button")
    public void i_click_the_qualifications_button() {
        WebElement qualificationsBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[6]"
        )));
        qualificationsBtn.click();
    }
    @Then("I should see the Qualifications screen")
    public void i_should_see_the_qualifications_screen() {

    }

}
