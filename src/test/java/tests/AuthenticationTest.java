package tests;

import driver.BaseTest;
import models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.DashboardPage;
import pages.HomePage;
import pages.components.NavigationMenu;

import java.util.Objects;
import java.util.stream.Stream;

import static constants.Constants.*;
import static helpers.GetUser.*;
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

    static Stream<Arguments> usersToTest() {
        return Stream.of(
                Arguments.of(admin(), ADMIN_DASHBOARD_PATH),
                Arguments.of(customer(), CUSTOMER_ACCOUNT_PATH)
        );
    }

    //Run tests for each position of the stream
    @ParameterizedTest(name = "Login as {0}")
    //Receive the test data from the previous stream
    @MethodSource("usersToTest")
    public void doLoginProcessWithUsers(User user, String expectedPath) {
        HomePage homePage = new HomePage(driver);
        NavigationMenu navigationMenu = new NavigationMenu(driver);

        String token = homePage.loginByAPIAndRetrieveSessionToken(user);
        homePage.goToHomePage();
        homePage.setSessionTokenInBrowser(token);
        homePage.goToBasePageWithPath(expectedPath);

        assertStringContainsOtherString(
                getCurrentURL(), expectedPath, URL_NOT_CONTAINED_ERROR + getCurrentURL());
        assertStringEqualsOtherString(
                user.name(), navigationMenu.retrieveUsernameInMenu(), USERNAME_DOES_NOT_MATCH);
    }
}
