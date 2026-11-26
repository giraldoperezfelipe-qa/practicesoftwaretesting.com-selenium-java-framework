package tests;

import driver.BaseTest;
import org.junit.jupiter.api.Test;
import pages.DashboardPage;
import pages.HomePage;
import pages.components.NavigationMenu;

import java.util.Objects;

import static constants.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

    public void assertStringEqualsOtherString
            (String firstString, String secondString, String errorMessage) {
        assertEquals(Objects
                .requireNonNull(firstString), secondString, errorMessage);
    }

    @Test
    public void doLoginProcessForAdmin() {
        HomePage homePage = new HomePage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);
        NavigationMenu navigationMenu = new NavigationMenu(driver);
        String sessionToken = homePage.loginByAPIAndRetrieveSessionToken();
        homePage.goToHomePage();
        homePage.setSessionTokenInBrowser(sessionToken);
        dashboardPage.goToDashboard();
        dashboardPage.waitForSalesChartToBeVisible();
        dashboardPage.waitForLatestOrdersTableToBeVisible();

        String currentUrl = getCurrentURL();
        assertStringContainsOtherString
                (currentUrl, ADMIN_DASHBOARD_PATH, URL_NOT_CONTAINED_ERROR + currentUrl);

        assertStringEqualsOtherString
                ("John Doe", navigationMenu.retrieveUsernameInMenu(), USERNAME_DOES_NOT_MATCH);
    }
}
