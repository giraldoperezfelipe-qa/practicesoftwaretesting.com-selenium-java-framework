package tests;

import driver.BaseTest;
import org.junit.jupiter.api.Test;
import pages.AuthenticationPage;
import pages.DashboardPage;
import pages.HomePage;

import java.util.Objects;

import static constants.Constants.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthenticationTest extends BaseTest {

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    public void assertStringContainsOtherString
            (String stringThatContains, String stringToCompare, String errorMessage) {
        assertTrue(
                Objects
                        .requireNonNull(stringThatContains)
                        .contains(stringToCompare),
                errorMessage
        );
    }

    @Test
    public void doLoginProcessWithEmailAndPasswordByUI() {
        HomePage homePage = new HomePage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);
        AuthenticationPage authenticationPage = new AuthenticationPage(driver);

        String currentUrl;
        homePage.goToBasePageWithURL("");
        homePage.clickLoginButton();
        authenticationPage.waitForLoginFormToBeVisible();

        currentUrl = getCurrentURL();
        assertStringContainsOtherString
                (currentUrl, AUTH_LOGIN_PATH, URL_NOT_CONTAINED_ERROR + currentUrl);

        authenticationPage.fillAndSendLoginForm
                ("admin@practicesoftwaretesting.com", "welcome01");

        /*
        dashboardPage.waitForSalesChartToBeVisible();
        dashboardPage.waitForLatestOrdersTableToBeVisible();

        currentUrl = getCurrentURL();
        assertStringContainsOtherString
                (currentUrl, ADMIN_DASHBOARD_PATH, URL_NOT_CONTAINED_ERROR + currentUrl);

         */
    }
}
