package infrastructure.selenium.Driver;

import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class DriverSetting {
    private final WebDriver driver;

    public DriverSetting(WebDriver driver) {
        this.driver = driver;
    }
    public Driver getDriver() {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        return new Driver(driver);
    }
}
