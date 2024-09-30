package application.service.extract;

import application.dto.PageElements;
import infrastructure.selenium.Driver.DriverController;
import infrastructure.selenium.Driver.WaitDriverController;
import infrastructure.selenium.css.BySelector;
import org.openqa.selenium.By;

public class ExtractPageElementsFactory {
    public static PageElements extractPage(DriverController driver, WaitDriverController waitDriver) {
        String page = BySelector.getSolvedProblemPage();
        waitDriver.visibilityOfElementLocated(page);
        return new PageElements(driver.findElements(By.cssSelector(page)));
    }
}
