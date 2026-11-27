package pages;

import helpers.ApiAuth;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

    public void goToBasePageWithPath(String path) {
        driver.get(getBaseUrl() + path);
    }

    //Override explicit wait, so it can receive no arguments
    protected Wait<WebDriver> explicitWait() {
        return explicitWait(2);
    }

    //Override explicit wait, so it can receive custom "seconds" argument
    protected Wait<WebDriver> explicitWait(int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    public void waitForElementToBeVisible(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public String loginByAPIAndRetrieveSessionToken(User user) {
        String token;

        try {
            token = ApiAuth.getAuthToken(user.email(), user.password());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return token;
    }

    public void setSessionTokenInBrowser(String sessionToken) {
        ((JavascriptExecutor) driver).executeScript(
                "window.localStorage.setItem('auth-token', arguments[0]);", sessionToken);
    }
}