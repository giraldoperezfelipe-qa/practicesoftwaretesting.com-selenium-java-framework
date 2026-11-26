package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

    protected Wait<WebDriver> explicitWait() {
        return explicitWait(2);
    }

    protected Wait<WebDriver> explicitWait(int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    public void waitForElementToBeVisible(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}