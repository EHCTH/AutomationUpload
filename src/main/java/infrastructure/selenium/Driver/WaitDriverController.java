package infrastructure.selenium.Driver;

public interface WaitDriverController {
    void visibilityOfElementLocated(String selector);
    void elementToBeClickable(String selector);
    void visibilityOfAllElementsLocatedBy(String selector);
}
