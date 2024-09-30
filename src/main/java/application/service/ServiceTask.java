package application.service;

import application.service.login.LoginService;
import application.service.login.LoginServiceManage;
import infrastructure.selenium.Driver.*;
import infrastructure.url.Url;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class ServiceTask implements TaskManger {
    private final LoginServiceManage loginServiceManage;
    private final DriverController driver;
    private final WaitDriverController waitDriver;
    public ServiceTask(LoginServiceManage loginService,
                       DriverController driver,
                       WaitDriverController waitDriver) {
        this.loginServiceManage = loginService;
        this.driver = driver;
        this.waitDriver = waitDriver;
    }

    @Override
    public void login() {
        loginServiceManage.login(driver, waitDriver);
    }

    @Override
    public void start() {
        try {
            String bojUrl = Url.getLoginUrl();
            driver.get(bojUrl);
            login();
            Thread.sleep(15000);
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
    }

    public static void main(String[] args) {
        TaskManger serviceTask = ServiceTaskFactory.createServiceTask(new ChromeDriver());
        serviceTask.start();
    }
}
