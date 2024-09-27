package infrastructure.selenium;

import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class DriverSetting {
    private final WebDriver driver;

    public DriverSetting(WebDriver driver) {
        this.driver = driver;
    }
    public void get(String url) {
        driver.get(url);
    }
    public WaitDriver ofWaitDriverByDurationOfTenSecond() {
        return new WaitDriver(driver, Duration.ofSeconds(10));
    }
}
