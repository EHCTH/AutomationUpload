package infrastructure.selenium.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitDriver implements WaitDriverController {
    private final WebDriverWait wait;
    public WaitDriver(WebDriver driver, Duration duration) {
        this.wait = new WebDriverWait(driver, duration);
    }
    public void visibilityOfElementLocated(String selector) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(selector)
        ));
    }
    public void elementToBeClickable(String selector) {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(selector)
        ));
    }

}