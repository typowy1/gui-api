package pl.example.gui.tests;

import io.qameta.allure.Step;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pl.example.gui.driver.browser.Browser;
import pl.example.gui.driver.browser.PageUrl;
import pl.example.gui.driver.manager.DriverSetup;
import propertiesConfig.ConfigurationProperties;

public class BaseTest {

    static final String APPLICATION_URL = ConfigurationProperties.getProperty("gui", "app.url");

    @Step("Setting up browser to: {browserType}, maximizing browser window, navigating to Home Page")
    @Parameters("browser")
    //browser parametr z pliku gui_test_suite.xml, jeśli go nie będzie lub uruchomimy testy z poziomu klasy to testy uruchomia sie na przgladarce podanej w propertiesach
    @BeforeMethod
    public void beforeTest(@Optional Browser browserType) {
        DriverSetup.setWebDriver(browserType);
        DriverSetup.getWebDriver();
        DriverSetup.getWebDriver().manage().window().maximize();
        PageUrl.goToPage(APPLICATION_URL);
    }

    @Step("Disposing browser")
    @AfterMethod
    public void afterTest() {
        DriverSetup.disposeDriver();
    }
}
