package application.service.profile;

import domain.algorithm.problem.AlgorithmTag;
import infrastructure.selenium.Driver.DriverController;
import infrastructure.selenium.Driver.WaitDriver;
import infrastructure.selenium.Driver.WaitDriverController;
import org.openqa.selenium.WebElement;

public interface ProfileManage {
    void enterMyPage(DriverController driver, WaitDriverController waitDriver);
    void enterMySolvePage(DriverController driver, WaitDriverController waitDriver);
    void enterMySolveSubmissionPage(DriverController driver, WaitDriverController waitDriver);
    String findAlgorithmTag(DriverController driver, WaitDriverController waitDriver);
    WebElement findFirstSolveElement(DriverController driver, WaitDriverController waitDriver);
}
