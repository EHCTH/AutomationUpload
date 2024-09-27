package infrastructure.selenium;

import org.openqa.selenium.WebDriver;

public class DriverSetting {
    private final WebDriver driver;
    public DriverSetting(WebDriver driver) {
        this.driver = driver;
    }
    public void get(String url) {
        driver.get(url);
    }
}
