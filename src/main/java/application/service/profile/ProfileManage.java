package application.service.profile;

import infrastructure.selenium.Driver.DriverController;
import infrastructure.selenium.Driver.WaitDriver;
import infrastructure.selenium.Driver.WaitDriverController;

public interface ProfileManage {
    void enterMyPage(DriverController driver, WaitDriverController waitDriver);
    void enterMySolvePage(DriverController driver, WaitDriverController waitDriver);
}
