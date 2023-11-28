package pl.example.gui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.example.gui.commonMethods.CommonMethods;
import pl.example.gui.waits.Waits;

public class CookiesConsentBarPage extends BasePage {

    @FindBy(css = "div.cky-consent-bar")
    private WebElement cookiesConsentBar;

    @FindBy(css = ".cky-notice-group .cky-btn-customize")
    private WebElement customizeButton;

    @FindBy(css = ".cky-notice-group .cky-btn-reject")
    private WebElement rejectAllButton;

    @FindBy(css = ".cky-notice-group .cky-btn-accept")
    private WebElement acceptAllButton;

    @FindBy(css = "p.cky-title")
    private WebElement noticeTitle;

    @FindBy(css = "p a.cky-policy")
    private WebElement cookiePolicyLink;

    @FindBy(css = "div.cky-preference-center")
    private WebElement cookiePreferenceModal;

    @Step("Click Accept all button")
    public void clickAcceptAllButton() {
        CommonMethods.clickOnElement(acceptAllButton);
        log().info("Clicked 'Accept all' button in cookies consent bar");
    }

    @Step("Click Customize button")
    public void clickCustomizeButton() {
        CommonMethods.clickOnElement(customizeButton);
        Waits.waitUntilElementIsVisible(cookiePreferenceModal);
        log().info("Clicked 'Customize' button in cookies consent bar");
    }

    @Step("Click Reject all button")
    public void clickRejectAllButton() {
        CommonMethods.clickOnElement(rejectAllButton);
        log().info("Clicked 'Reject all' button");
    }

    public void clickCookiePolicyLink() {
        CommonMethods.clickOnElement(cookiePolicyLink);
    }

    public WebElement getCookiesConsentBar() {
        return cookiesConsentBar;
    }

    public WebElement getNoticeTitle() {
        return noticeTitle;
    }

    public WebElement getCookiePolicyLink() {
        return cookiePolicyLink;
    }
}
