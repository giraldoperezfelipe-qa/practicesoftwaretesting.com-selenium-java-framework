package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;

public final class DriverFactory {

    //ThreadLocal resolves problems when handling parallel tests,
    //so 2 or more sessions don't share a same driver state
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverFactory() {
    }

    public static WebDriver createDriver(String browser) {
        WebDriver driver;
        switch (browser.toLowerCase()) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "chrome":
            default:
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                driver = new ChromeDriver(options);
        }
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        DRIVER.set(driver);
        return driver;
    }

    public static void quitDriver(WebDriver driver) {
        driver.quit();
        DRIVER.remove();
    }
}