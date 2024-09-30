package application.service.profile;

import infrastructure.selenium.Driver.DriverController;
import infrastructure.selenium.Driver.WaitDriverController;
import infrastructure.selenium.css.BySelector;
import org.openqa.selenium.By;

public class ProfileService implements ProfileManage{
    @Override
    public void enterMyPage(DriverController driver, WaitDriverController waitDriver) {
        String myPage = BySelector.getUserPage();
        waitDriver.elementToBeClickable(myPage);
        driver.clickButton(By.cssSelector(myPage));
    }

    @Override
    public void enterMySolvePage(DriverController driver, WaitDriverController waitDriver) {
        String userSolvedPage = BySelector.getUserSolvedPage();
        waitDriver.elementToBeClickable(userSolvedPage);
        driver.clickButton(By.cssSelector(userSolvedPage));

    }
}
