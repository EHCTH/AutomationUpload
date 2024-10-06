package application.service;

import application.service.login.LoginService;
import application.service.login.LoginServiceManage;
import application.service.profile.ProfileService;
import infrastructure.github.hanlder.ResponseHandler;
import infrastructure.github.controller.UploadManage;
import infrastructure.github.controller.GithubController;
import infrastructure.github.dto.Github;
import infrastructure.github.service.FileUploaderService;
import infrastructure.github.service.HttpClientService;
import infrastructure.parse.service.CookieManager;
import infrastructure.parse.service.LinkExtractor;
import infrastructure.parse.domain.Parse;
import infrastructure.parse.service.ProblemExtractor;
import infrastructure.selenium.Driver.DriverController;
import infrastructure.selenium.Driver.DriverSetting;
import infrastructure.selenium.Driver.WaitDriver;
import infrastructure.selenium.Driver.WaitDriverController;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class ServiceControllerFactory {
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

        FileUploaderService fileUploaderService = new FileUploaderService(
                new HttpClientService(),
                new ResponseHandler(),
                new Github()
        );
        UploadManage githubUpload = new GithubController(
                fileUploaderService
        );

        return new ServiceController(
                loginService,
                profileService,
                problemExtractor,
                linkExtractor,
                githubUpload
        );
    }
}
