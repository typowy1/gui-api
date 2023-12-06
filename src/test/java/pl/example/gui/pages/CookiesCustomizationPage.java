package pl.example.gui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.example.gui.commonMethods.CommonMethods;
import pl.example.gui.waits.Waits;

import java.util.List;

public class CookiesCustomizationPage extends BasePage {

    @FindBy(css = "div.cky-preference-center")
    private WebElement cookiePreferenceModal;

    @FindBy(css = "span.cky-preference-title")
    private WebElement cookiePreferenceModalHeader;

    @FindBy(css = "button.cky-btn-close")
    private WebElement closeCookieCustomizationButton;

    @FindBy(css = ".cky-prefrence-btn-wrapper button.cky-btn-reject")
    private WebElement rejectAllButton;

    @FindBy(css = ".cky-prefrence-btn-wrapper button.cky-btn-preferences")
    private WebElement acceptPreferredButton;

    @FindBy(css = ".cky-prefrence-btn-wrapper button.cky-btn-accept")
    private WebElement acceptAllButton;

    @FindBy(css = ".cky-prefrence-btn-wrapper > button")
    private List<WebElement> buttonsList;

    @FindBy(id = "ckySwitchfunctional")
    private WebElement preferencesToggle;

    @FindBy(id = "ckySwitchperformance")
    private WebElement performanceToggle;

    @FindBy(css = ".cky-accordion-btn")
    private List<WebElement> accordionButtons;

    @Step("Click Accept all button")
    public void clickAcceptAllButton() {
//        CommonMethods.clickOnMovingElement(acceptAllButton);
        try {
            CommonMethods.clickOnElement(acceptAllButton);
        } catch (ElementClickInterceptedException e) {
            CommonMethods.clickOnElement(acceptAllButton);
        }
        log().info("Clicked 'Accept all' button in cookies customization window");
    }

    @Step("Click Reject all button")
    public void clickRejectAllButton() {
        CommonMethods.clickOnMovingElement(rejectAllButton);
        log().info("Clicked 'Reject all' button in cookies customization window");
    }

    @Step("Click Preferences toggle")
    public void clickPreferencesToggle() {
        CommonMethods.clickOnToggleElement(preferencesToggle);
        log().info("Clicked on preferences toggle in cookies customization window");
    }

    @Step("Click Performance toggle")
    public void clickPerformanceToggle() {
        CommonMethods.scrollToElement(performanceToggle);
        CommonMethods.clickOnToggleElement(performanceToggle);
        log().info("Clicked on performance toggle in cookies customization window");
    }

    @Step("Click close cookie customization button")
    public void closeCookiePreferencesModal() {
        CommonMethods.clickOnMovingElement(closeCookieCustomizationButton);
        log().info("Clicked close button in cookie customization modal");
        Waits.waitUntilElementWillBeInvisible(cookiePreferenceModal);
    }

    @Step("Getting text labels from cookie preference modal buttons")
    public List<String> getTextFromPreferenceModalButtons() {
        List<String> cookieNoticeButtonLabels = CommonMethods.getTextValuesFromWebElementsList(buttonsList);
        log().info("Actual cookie preference modal button labels: {}", cookieNoticeButtonLabels);
        return cookieNoticeButtonLabels;
    }

    @Step("Getting text labels from cookie preference modal accordion buttons")
    public List<String> getTextFromAccordionButtons() {
        List<String> accordionButtonLabels = CommonMethods.getTextValuesFromWebElementsList(accordionButtons);
        log().info("Actual cookie preference modal button labels: {}", accordionButtonLabels);
        return accordionButtonLabels;
    }

    public WebElement getCookiePreferenceModal() {
        return cookiePreferenceModal;
    }

    public WebElement getPreferencesToggle() {
        return preferencesToggle;
    }

    public WebElement getCookiePreferenceModalHeader() {
        return cookiePreferenceModalHeader;
    }
}
