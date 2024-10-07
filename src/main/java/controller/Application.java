package controller;

import application.service.controller.ServiceControllerFactory;
import application.service.controller.ControllerManager;
import org.openqa.selenium.chrome.ChromeDriver;

public class Application {
    public static void main(String[] args) {
        ControllerManager service = ServiceControllerFactory.createServiceTask(new ChromeDriver());
        service.start();
    }
}
