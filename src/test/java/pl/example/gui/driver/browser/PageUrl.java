package pl.example.gui.driver.browser;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.example.gui.driver.manager.DriverSetup;

public class PageUrl {
    private static Logger logger = LogManager.getLogger(PageUrl.class);

    @Step("Navigating to URL: {pageUrl}")
    public static void goToPage(String pageUrl) {
        DriverSetup.getWebDriver().navigate().to(pageUrl);
        logger.info("Navigated to: {}", pageUrl);
    }
}
