package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AuthenticationPage extends BasePage {
    private final By loginForm = By.className("auth-container");
    private final By emailInput = By.cssSelector("[data-test='email']");
    private final By passwordInput = By.cssSelector("[data-test='password']");
    private final By loginButton = By.cssSelector("[data-test='login-submit']");

    public AuthenticationPage(WebDriver driver) {
        super(driver);
    }

    public void waitForLoginFormToBeVisible() {
        waitForElementToBeVisible(loginForm);
    }

    public void fillAndSendLoginForm(String email, String password) {
        waitForElementToBeVisible(emailInput);
        waitForElementToBeVisible(passwordInput);
        driver.findElement(emailInput).clear();
        driver.findElement(passwordInput).clear();
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();
    }
}
