package ui.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverUtil {
    public static WebDriver getDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Runs Chrome in headless mode
        options.addArguments("--no-sandbox"); // Disables sandboxing (for security)
        options.addArguments("--disable-dev-shm-usage"); // Disables shared memory usage (avoids crashes)
        options.addArguments("--disable-gpu"); // Disables GPU hardware acceleration
        options.addArguments("--disable-extensions"); // Disables extensions
        options.addArguments("--disable-web-security", "--ignore-certificate-errors");
        return new ChromeDriver(options);
    }
}
