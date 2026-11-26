package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static helpers.GetConfigProperties.getBaseUrl;

public abstract class BasePage {
    protected final WebDriver driver;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToBasePageWithURL(String path) {
        driver.get(getBaseUrl() + path);
    }

    public Wait<WebDriver> explicitWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(2));
    }
}