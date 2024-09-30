package infrastructure.selenium.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class Driver implements DriverController {
    private final WebDriver driver;
    public Driver(WebDriver driver) {
        this.driver = driver;
    }
    @Override
    public void get(String url) {
        driver.get(url);
    }
    @Override
    public WebDriver getWebDriver() {
        return driver;
    }

    @Override
    public void quit() {
        driver.quit();
    }
    @Override
    public void writeText(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }

    @Override
    public void clickButton(By by) {
        driver.findElement(by).click();
    }
}
