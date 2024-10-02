package application.service.login;

import application.service.UserService;
import domain.cookie.SeleniumCookie;
import domain.user.User;
import infrastructure.selenium.Driver.WaitDriverController;
import infrastructure.selenium.Driver.DriverController;
import infrastructure.selenium.css.BySelector;
import infrastructure.url.Url;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginService implements LoginServiceManage {
    private final UserService userService;
    private final DriverController driver;
    private final WaitDriverController waitDriver;
    private final Logger logger = LoggerFactory.getLogger(LoginService.class);
    public LoginService(UserService userService, DriverController driver, WaitDriverController waitDriver) {
        this.userService = userService;
        this.driver = driver;
        this.waitDriver = waitDriver;
    }


    @Override
    public void login() {
        String bojUrl = Url.getLoginUrl();
        driver.get(bojUrl);
        User user = userService.createUser();
        String cssId = BySelector.getId();
        String cssPass = BySelector.getPass();
        String submitButton = BySelector.getLoginSubmitButton();

        waitDriver.visibilityOfElementLocated(cssId);
        driver.writeText(By.cssSelector(cssId), user.getId());

        waitDriver.visibilityOfElementLocated(cssPass);
        driver.writeText(By.cssSelector(cssPass), user.getPass());

        waitDriver.elementToBeClickable(submitButton);
        driver.clickButton(By.cssSelector(submitButton));
        logger.info("로그인 완료");
    }

    @Override
    public SeleniumCookie extractCookies() {
        return new SeleniumCookie(driver.getCookie());
    }

    @Override
    public void logout() {
        driver.quit();
    }
}
