package infrastructure.selenium.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public interface DriverController {
    void get(String url);
    WebDriver getWebDriver();
    void writeText(By by, String text);
    void clickButton(By by);
    void quit();
}
