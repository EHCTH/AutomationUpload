package infrastructure.selenium.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public interface DriverController {
    void get(String url);
    WebDriver getWebDriver();
    void writeText(By by, String text);
    void clickButton(By by);
    void quit();
    Set<Cookie> getCookie();
    List<WebElement> findElements(By by);
    WebElement findElement(By by);
    WebElement findFirstSolveElement(By by);
}
