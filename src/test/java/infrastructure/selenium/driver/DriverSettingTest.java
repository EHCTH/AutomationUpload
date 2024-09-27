package infrastructure.selenium.driver;

import infrastructure.selenium.Driver.Driver;
import infrastructure.selenium.Driver.DriverSetting;
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
        Driver driver = driverSetting.getDriver();
        Mockito.verify(driver).get(testUrl);
    }
}
