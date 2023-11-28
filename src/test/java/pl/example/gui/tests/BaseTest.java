package pl.example.gui.tests;

import io.qameta.allure.Step;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pl.example.gui.driver.browser.Browser;
import pl.example.gui.driver.browser.BrowserUtils;
import pl.example.gui.driver.browser.PageUrl;
import pl.example.gui.driver.manager.DriverSetup;
import pl.example.gui.pages.CookiesConsentBarPage;
import propertiesConfig.ConfigurationProperties;

public class BaseTest {

    CookiesConsentBarPage cookiesConsentBarPage;

    protected static final String APPLICATION_URL = ConfigurationProperties.getProperty("gui", "app.url");

    @Step("Tests setup")
    @Parameters("browser")
    //browser parametr z pliku gui_test_suite.xml lub parametru -Dbrowser, jeśli go nie będzie lub uruchomimy testy z poziomu klasy to testy uruchomia sie na przgladarce podanej w propertiesach
    @BeforeMethod
    public void beforeTest(@Optional Browser browserType) {
        DriverSetup.setWebDriver(browserType);
        DriverSetup.getWebDriver();
        BrowserUtils.setWindowSize();
        PageUrl.goToPage(APPLICATION_URL);

        cookiesConsentBarPage = new CookiesConsentBarPage();
        cookiesConsentBarPage.clickAcceptAllButton();
    }

    @Step("Disposing browser")
    @AfterMethod
    public void afterTest() {
        DriverSetup.disposeDriver();
    }
}
