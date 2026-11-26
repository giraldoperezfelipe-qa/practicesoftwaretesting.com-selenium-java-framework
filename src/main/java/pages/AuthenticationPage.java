package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AuthenticationPage extends BasePage {
    private final By loginForm = By.className("auth-container");

    public AuthenticationPage(WebDriver driver) {
        super(driver);
    }

    public void waitForLoginFormToBeVisible() {
        explicitWait().until(ExpectedConditions.visibilityOfElementLocated(loginForm));
    }
}
