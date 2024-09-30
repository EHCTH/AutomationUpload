package application.service;

import application.service.login.LoginServiceManage;
import application.service.profile.ProfileManage;
import domain.cookie.SeleniumCookie;
import infrastructure.selenium.Driver.*;
import infrastructure.url.Url;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceTask implements TaskManger {
    private final Logger logger = LoggerFactory.getLogger(ServiceTask.class);
    private final LoginServiceManage loginService;
    private final DriverController driver;
    private final WaitDriverController waitDriver;
    private final ProfileManage profileService;
    public ServiceTask(LoginServiceManage loginService,
                       DriverController driver,
                       WaitDriverController waitDriver,
                       ProfileManage profileService
                       ) {
        this.loginService = loginService;
        this.driver = driver;
        this.waitDriver = waitDriver;
        this.profileService = profileService;
    }

    @Override
    public void login() {
        loginService.login(driver, waitDriver);
        logger.info("로그인 완료");
    }

    @Override
    public void start() {
        try {
            String bojUrl = Url.getLoginUrl();
            driver.get(bojUrl);
            login();
            Thread.sleep(25000);
            SeleniumCookie cookies = new SeleniumCookie(driver.getCookie());
            enterMyPage();
            enterMysSolvedPage();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally {
            quit();
        }
    }


    @Override
    public void quit() {
        driver.quit();
        logger.info("서비스 종료");
    }

    @Override
    public void enterMyPage() {
        profileService.enterMyPage(driver, waitDriver);
        logger.info("나의 페이지 클릭 완료");
    }

    @Override
    public void enterMysSolvedPage() {
        profileService.enterMySolvePage(driver, waitDriver);
        logger.info("나의 맞은 문제 클릭 완료");
    }
}
