package pl.example.gui.driver.browser;

import io.qameta.allure.Step;
import pl.example.gui.driver.manager.DriverSetup;

public class BrowserUtils {
    @Step("Maximizing browser window")
    public static void setWindowSize() {
        DriverSetup.getWebDriver().manage().window().maximize();
    }
}
