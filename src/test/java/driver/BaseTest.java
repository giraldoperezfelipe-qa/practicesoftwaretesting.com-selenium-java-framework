package driver;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public abstract class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = DriverFactory.createDriver(System.getProperty("browser", "chrome"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            DriverFactory.quitDriver(driver);
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}