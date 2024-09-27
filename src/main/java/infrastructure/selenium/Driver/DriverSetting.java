package infrastructure.selenium.Driver;

import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class DriverSetting {
    private final WebDriver driver;

    public DriverSetting(WebDriver driver) {
        this.driver = driver;
    }
    public WebDriver getDriver() {
        return driver;
    }
    public WaitDriver ofWaitDriverByDurationOfTenSecond() {
        return new WaitDriver(driver, Duration.ofSeconds(10));
    }
}
