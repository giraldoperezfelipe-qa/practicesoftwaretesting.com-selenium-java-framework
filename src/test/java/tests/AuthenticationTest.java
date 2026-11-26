package tests;

import driver.BaseTest;
import org.junit.jupiter.api.Test;
import pages.AuthenticationPage;
import pages.HomePage;

import java.util.Objects;

import static constants.Constants.AUTH_LOGIN_PATH;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthenticationTest extends BaseTest {
    @Test
    void doLoginProcessByUI() {
        HomePage homePage = new HomePage(driver);
        AuthenticationPage authenticationPage = new AuthenticationPage(driver);
        homePage.goToBasePageWithURL("");
        homePage.clickLoginButton();
        authenticationPage.waitForLoginFormToBeVisible();
        String currentUrl = driver.getCurrentUrl();

        assertTrue(Objects.requireNonNull(currentUrl).contains(AUTH_LOGIN_PATH),
                "Expected URL to match, but was: " + currentUrl);
    }
}
