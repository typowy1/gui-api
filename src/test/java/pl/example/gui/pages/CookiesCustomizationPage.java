package pl.example.gui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.example.gui.commonMethods.CommonMethods;
import pl.example.gui.waits.Waits;

public class CookiesCustomizationPage extends BasePage {

    @FindBy(css = "div.cky-preference-center")
    private WebElement cookiePreferenceModal;

    @FindBy(css = "button.cky-btn-close")
    private WebElement closeCookieCustomizationButton;

    @FindBy(css = ".cky-prefrence-btn-wrapper button.cky-btn-reject")
    private WebElement rejectAllButton;

    @FindBy(css = ".cky-prefrence-btn-wrapper button.cky-btn-preferences")
    private WebElement acceptPreferredButton;

    @FindBy(css = ".cky-prefrence-btn-wrapper button.cky-btn-accept")
    private WebElement acceptAllButton;

    @FindBy(id = "ckySwitchfunctional")
    private WebElement preferencesToggle;

    @FindBy(id = "ckySwitchperformance")
    private WebElement performanceToggle;


    @Step("Click Accept all button")
    public void clickAcceptAllButton() {
        Waits.waitUntilElementStopsMoving(cookiePreferenceModal);
        CommonMethods.clickOnElement(acceptAllButton);
        log().info("Clicked 'Accept all' button in cookies customization window");
    }

    @Step("Click Reject all button")
    public void clickRejectAllButton() {
        Waits.waitUntilElementStopsMoving(cookiePreferenceModal);
        CommonMethods.clickOnElement(rejectAllButton);
        log().info("Clicked 'Reject all' button in cookies customization window");
    }

    @Step("Click Preferences toggle")
    public void clickPreferencesToggle() {
        Waits.waitUntilElementStopsMoving(cookiePreferenceModal);
        CommonMethods.clickOnToggleElement(preferencesToggle);
        log().info("Clicked on preferences toggle in cookies customization window");
    }

    @Step("Click Performance toggle")
    public void clickPerformanceToggle() {
        Waits.waitUntilElementStopsMoving(cookiePreferenceModal);
        CommonMethods.scrollToElement(performanceToggle);
        CommonMethods.clickOnToggleElement(performanceToggle);
        log().info("Clicked on performance toggle in cookies customization window");
    }

    @Step("Click close cookie customization button")
    public void closeCookiePreferencesModal() {
        Waits.waitUntilElementStopsMoving(cookiePreferenceModal);
        CommonMethods.clickOnElementUsingJS(closeCookieCustomizationButton);
        log().info("Clicked close button in cookie customization modal");
        Waits.waitUntilElementWillBeInvisible(cookiePreferenceModal);
    }

    public WebElement getCookiePreferenceModal() {
        return cookiePreferenceModal;
    }

    public WebElement getPreferencesToggle() {
        return preferencesToggle;
    }
}
