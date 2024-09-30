package application.service.login;

import infrastructure.selenium.Driver.WaitDriverController;
import infrastructure.selenium.Driver.DriverController;

public interface LoginServiceManage {
    void login(DriverController driver, WaitDriverController waitDriver);

}
