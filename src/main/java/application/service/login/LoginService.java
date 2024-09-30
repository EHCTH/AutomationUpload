package application.service.login;

import application.service.UserService;
import domain.user.User;
import infrastructure.selenium.Driver.WaitDriverController;
import infrastructure.selenium.Driver.DriverController;
import infrastructure.selenium.css.BySelector;
import org.openqa.selenium.By;

public class LoginService implements LoginServiceManage {
    private final UserService userService;

    public LoginService(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void login(DriverController driver, WaitDriverController waitDriver) {
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
    }
}
