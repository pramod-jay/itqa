package org.itqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class Main {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        //Connect with website
        driver.get("https://web.facebook.com/");

        //Get website data(Browser information)
        driver.getTitle();

        //Waiting for loading elements
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        //Fetch website's elements
        WebElement textBox = driver.findElement(By.name("email"));
        WebElement button = driver.findElement(By.name("login"));
        WebElement password = driver.findElement(By.name("pass"));

        //Run the elements
        textBox.sendKeys("pramoddilshan470@gmail.com");
        password.sendKeys("dilshan");
        button.click();

//       driver.quit();
    }
}