package application.service;

import application.service.login.LoginService;
import application.service.login.LoginServiceManage;
import application.service.profile.ProfileService;
import infrastructure.parse.CookieManager;
import infrastructure.parse.LinkExtractor;
import infrastructure.parse.Parse;
import infrastructure.parse.ProblemExtractor;
import infrastructure.selenium.Driver.DriverController;
import infrastructure.selenium.Driver.DriverSetting;
import infrastructure.selenium.Driver.WaitDriver;
import infrastructure.selenium.Driver.WaitDriverController;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class ServiceTaskFactory {
    public static TaskManger createServiceTask(WebDriver webDriver) {
        DriverSetting driverSetting = new DriverSetting(webDriver);
        DriverController driver = driverSetting.getDriver();
        WaitDriverController waitDriver = new WaitDriver(driver.getWebDriver(), Duration.ofSeconds(10));
        LoginServiceManage loginService = new LoginService(
                new UserService(),
                driver,
                waitDriver

        );
        Parse parse = new Parse(new CookieManager());
        ProfileService profileService = new ProfileService(driver, waitDriver);
        LinkExtractor linkExtractor = new LinkExtractor(driver, waitDriver, parse);
        ProblemExtractor problemExtractor = new ProblemExtractor(parse);
        return new ServiceTask(
                loginService,
                profileService,
                problemExtractor,
                linkExtractor
        );
    }
}
