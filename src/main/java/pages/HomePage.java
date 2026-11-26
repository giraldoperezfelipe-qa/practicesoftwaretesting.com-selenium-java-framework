package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
    private final By loginButton = By.cssSelector("[data-test='nav-sign-in']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickLoginButton() {
        explicitWait().until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        driver.findElement(loginButton).click();
    }
}
