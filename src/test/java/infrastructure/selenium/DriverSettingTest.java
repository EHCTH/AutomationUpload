package infrastructure.selenium;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openqa.selenium.WebDriver;


public class DriverSettingTest {

    private WebDriver driver;
    private DriverSetting driverSetting;

    @BeforeEach
    public void setUp() {
        driver = Mockito.mock(WebDriver.class);
        driverSetting = new DriverSetting(driver);
    }

    @Test
    public void driverShouldNavigateToUrl() {
        String testUrl = "https://example.com";
        driverSetting.get(testUrl);
        Mockito.verify(driver).get(testUrl);
    }
}
