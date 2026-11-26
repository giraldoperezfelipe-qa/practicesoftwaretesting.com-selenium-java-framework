package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {
    private final By salesChart = By.className("chart");
    private final By latestOrdersTable = By.className("table");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void goToDashboard() {
        goToBasePageWithPath("/admin/dashboard");
    }

    public void waitForSalesChartToBeVisible() {
        waitForElementToBeVisible(salesChart);
    }

    public void waitForLatestOrdersTableToBeVisible() {
        waitForElementToBeVisible(latestOrdersTable);
    }
}
