package application;

import application.service.ServiceTaskFactory;
import application.service.TaskManger;
import org.openqa.selenium.chrome.ChromeDriver;

public class Application {
    public static void main(String[] args) {
        TaskManger service = ServiceTaskFactory.createServiceTask(new ChromeDriver());
        service.start();
    }
}
