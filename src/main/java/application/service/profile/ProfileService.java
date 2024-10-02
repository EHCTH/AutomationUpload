package application.service.profile;

import domain.algorithm.problem.AlgorithmTag;
import infrastructure.selenium.Driver.DriverController;
import infrastructure.selenium.Driver.WaitDriverController;
import infrastructure.selenium.css.BySelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.swing.text.Element;
import java.util.List;

public class ProfileService implements ProfileManage {
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

    @Override
    public void enterMySolveSubmissionPage(DriverController driver, WaitDriverController waitDriver) {
        String submit = BySelector.getMySubmit();
        waitDriver.elementToBeClickable(submit);
        driver.clickButton(By.cssSelector(submit));

    }

    @Override
    public String findAlgorithmTag(DriverController driver, WaitDriverController waitDriver) {
        String algorithmTag = BySelector.getAlgorithmTag();
        waitDriver.visibilityOfElementLocated(algorithmTag);
        WebElement element = driver.findElement(By.cssSelector(algorithmTag));
        return element.getText();

    }

    @Override
    public WebElement findFirstSolveElement(DriverController driver, WaitDriverController waitDriver) {
        String statusTable = BySelector.getStatusTable();
        waitDriver.visibilityOfAllElementsLocatedBy(statusTable);
        return driver.findFirstSolveElement(By.cssSelector(statusTable));
    }


}
