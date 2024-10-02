package application.service;

import application.service.login.LoginService;
import application.service.login.LoginServiceManage;
import application.service.profile.ProfileManage;
import application.service.profile.ProfileService;
import infrastructure.parse.ProblemExtractor;
import infrastructure.selenium.Driver.DriverController;
import infrastructure.selenium.Driver.DriverSetting;
import infrastructure.selenium.Driver.WaitDriver;
import infrastructure.selenium.Driver.WaitDriverController;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class ServiceTaskFactory {
    public static TaskManger createServiceTask(WebDriver webDriver) {
        LoginServiceManage loginService = new LoginService(
                new UserService()
        );
        DriverSetting driverSetting = new DriverSetting(
                webDriver
        );
        DriverController driver = driverSetting.getDriver();
        WaitDriverController waitDriver = new WaitDriver(driver.getWebDriver(), Duration.ofSeconds(10));
        ProfileService profileService = new ProfileService();
        ProblemExtractor problemExtractor = new ProblemExtractor();
        return new ServiceTask(
                loginService,
                driver,
                waitDriver,
                profileService,
                problemExtractor
        );
    }
}
