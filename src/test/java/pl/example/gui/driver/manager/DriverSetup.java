package pl.example.gui.driver.manager;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pl.example.gui.driver.browser.Browser;
import pl.example.gui.driver.browser.BrowserSetup;
import pl.example.gui.driver.listeners.WebDriverEventListenerRegistrar;
import propertiesConfig.ConfigurationProperties;

import static pl.example.gui.driver.browser.Browser.FIREFOX;

public class DriverSetup {
    //  wzorzec projektowy gwarantujący istnienie tylko jednego obiektu danego rodzaju.
    private static WebDriver driver;
    private static boolean remoteRun = Boolean.parseBoolean(ConfigurationProperties.getProperty("grid", "is.remote.run"));
    private static Browser browser = Browser.valueOf(ConfigurationProperties.getProperty("gui", "browser"));

    //Dwie zmiennej instancj klasy ThreadLocal przechowujące kolejno instancję obiektu WebDriver oraz BrowserType dla danego wątku
    private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<Browser> browserTypeThreadLocal = new ThreadLocal<>();

    private DriverSetup() {
    }

    //Metoda służy od ustawiania typu przeglądarki dla danego wątku, jezeli browserType = null(czyli w pliku test suite nie ma podanej przglądarki to, pobieramy ją z propertiesów
    @Step("Setting up browser to: {browserType}")
    public static void setWebDriver(Browser browserType) {
        WebDriver webDriverBrowser = null;

        if (browserType == null) {
            browserType = browser;
            webDriverBrowser = new BrowserSetup(browserType, remoteRun).getBrowser();

        } else {
            webDriverBrowser = new BrowserSetup(browserType, remoteRun).getBrowser();
        }
//       EventListener generuje zbytdużą ilość logów, mozna odkomentować w razie potrzeby
//        webDriverBrowser = WebDriverEventListenerRegistrar.registerWebDriverEventListener(webDriverBrowser);
        browserTypeThreadLocal.set(browserType);
        webDriverThreadLocal.set(webDriverBrowser);
    }

    public static WebDriver getWebDriver() {

        if (webDriverThreadLocal.get() == null) {
            throw new IllegalStateException("WebDriver Instance was null! Please create instance of WebDriver using setWebDriver!");
        }

        //Zwrócenie instancji WebDrivera dla danego wątku
        return webDriverThreadLocal.get();
    }

    public static void disposeDriver() {
        webDriverThreadLocal.get().close();

        if (!browserTypeThreadLocal.get().equals(FIREFOX)) {
            webDriverThreadLocal.get().quit();
        }

        webDriverThreadLocal.remove();
        browserTypeThreadLocal.remove();
    }
}
