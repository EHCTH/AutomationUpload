package infrastructure.selenium.Driver;

public interface WaitDriverController {
    void visibilityOfElementLocated(String selector);
    void elementToBeClickable(String selector);
}
