package pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class NavigationMenu extends BasePage {
    private final By loginButton = By.cssSelector("[data-test='nav-sign-in']");
    private final By usernameInMenu = By.cssSelector("[data-test='nav-menu']");

    public NavigationMenu(WebDriver driver) {
        super(driver);
    }

    public String retrieveUsernameInMenu() {
        return driver.findElement(usernameInMenu).getText();
    }
}
