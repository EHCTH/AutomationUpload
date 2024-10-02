package application.service.profile;

import infrastructure.selenium.Driver.DriverController;
import infrastructure.selenium.Driver.WaitDriverController;
import infrastructure.selenium.css.BySelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProfileService implements ProfileManage {
    private final DriverController driver;
    private final WaitDriverController waitDriver;
    private Logger logger = LoggerFactory.getLogger(ProfileService.class);
    public ProfileService(DriverController driver, WaitDriverController waitDriver) {
        this.driver = driver;
        this.waitDriver = waitDriver;
    }
    @Override
    public void enterMyPage() {
        String myPage = BySelector.getUserPage();
        waitDriver.elementToBeClickable(myPage);
        driver.clickButton(By.cssSelector(myPage));
        logger.info("나의 페이지 클릭 완료");
    }

    @Override
    public void enterMySolvePage() {
        String userSolvedPage = BySelector.getUserSolvedPage();
        waitDriver.elementToBeClickable(userSolvedPage);
        driver.clickButton(By.cssSelector(userSolvedPage));
        logger.info("나의 맞은 문제 클릭 완료");
    }

    @Override
    public void enterMySolveSubmissionPage() {
        String submit = BySelector.getMySubmit();
        waitDriver.elementToBeClickable(submit);
        driver.clickButton(By.cssSelector(submit));

    }

    @Override
    public void enterProblem(String url) {
        driver.get(url);
    }

    @Override
    public String findAlgorithmTag() {
        String algorithmTag = BySelector.getAlgorithmTag();
        waitDriver.visibilityOfElementLocated(algorithmTag);
        WebElement element = driver.findElement(By.cssSelector(algorithmTag));
        return element.getText();

    }

    @Override
    public WebElement findFirstSolveElement() {
        String statusTable = BySelector.getStatusTable();
        waitDriver.visibilityOfAllElementsLocatedBy(statusTable);
        return driver.findFirstSolveElement(By.cssSelector(statusTable));
    }


}
