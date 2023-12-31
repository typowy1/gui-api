package pl.example.gui.tests;

import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pl.example.gui.commons.CookiesConstants;
import pl.example.gui.driver.browser.Browser;
import pl.example.gui.driver.browser.BrowserUtils;
import pl.example.gui.driver.browser.PageUrl;
import pl.example.gui.driver.manager.DriverSetup;
import pl.example.gui.pages.CookiesConsentBarPage;
import pl.example.gui.pages.CookiesCustomizationPage;
import pl.example.gui.pages.TopMenuPage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CookiePreferenceTests extends BaseTest {

    CookiesConsentBarPage cookiesConsentBarPage;

    CookiesCustomizationPage cookiesCustomizationPage;

    TopMenuPage topMenuPage;

    @Override
    @Step("Tests setup - cookies consent bar not closed")
    @Parameters("browser")
    @BeforeMethod
    public void beforeTest(@Optional Browser browserType) {
        DriverSetup.setWebDriver(browserType);
        DriverSetup.getWebDriver();
        BrowserUtils.setWindowSize();
        PageUrl.goToPage(APPLICATION_URL);
        cookiesConsentBarPage = new CookiesConsentBarPage();
    }

    @TmsLink("ID5512")
    @Severity(SeverityLevel.NORMAL)
    @Test()
    @Description("Verify that cookies notification is no longer displayed after accepting all cookies")
    public void isCookiesConsentBarNotDisplayedAfterAccepting() {
        cookiesConsentBarPage.clickAcceptAllButton();

        assertThat(cookiesConsentBarPage.getCookiesConsentBar().isDisplayed()).isFalse();
    }

    @Issue("Bug 312")
    @TmsLink("ID5513")
    @Severity(SeverityLevel.NORMAL)
    @Test()
    @Description("Verify that cookies notification is no longer displayed after rejecting all cookies")
    public void consentBarDisplayedAfterRejectingCookiesFailedTest() {
        cookiesConsentBarPage.clickCustomizeButton();
        cookiesCustomizationPage = new CookiesCustomizationPage();
        cookiesCustomizationPage.clickRejectAllButton();

        assertThat(cookiesConsentBarPage.getCookiesConsentBar().isDisplayed()).isTrue();
    }

    @TmsLink("ID5514")
    @Severity(SeverityLevel.CRITICAL)
    @Test()
    @Description("Verify that cookie customization modal can be opened and closed")
    public void openAndCloseCookieCustomizationModal() {
        cookiesConsentBarPage.clickCustomizeButton();
        cookiesCustomizationPage = new CookiesCustomizationPage();

        assertThat(cookiesCustomizationPage.getCookiePreferenceModal().isDisplayed()).isTrue();

        cookiesCustomizationPage.closeCookiePreferencesModal();

        assertThat(cookiesCustomizationPage.getCookiePreferenceModal().isDisplayed()).isFalse();
    }

    @TmsLink("ID5515")
    @Severity(SeverityLevel.NORMAL)
    @Test()
    @Description("Verify that preferences toggle is disabled by default and can be enabled")
    public void swapPreferencesToggleToEnabled() {
        cookiesConsentBarPage.clickCustomizeButton();
        cookiesCustomizationPage = new CookiesCustomizationPage();

        assertThat(cookiesCustomizationPage.getPreferencesToggle().getAttribute("aria-label"))
                .isEqualTo(CookiesConstants.PREFERENCE_TOGGLE_DISABLED);

        cookiesCustomizationPage.clickPreferencesToggle();

        assertThat(cookiesCustomizationPage.getPreferencesToggle().getAttribute("aria-label"))
                .isEqualTo(CookiesConstants.PREFERENCE_TOGGLE_ENABLED);
    }

    @TmsLink("ID5516")
    @Severity(SeverityLevel.NORMAL)
    @Test()
    @Description("Verify that cookies notification is still displayed after navigating outside of the main menu")
    public void isCookiesConsentBarDisplayedPersistently() {
        topMenuPage = new TopMenuPage();
        topMenuPage.clickOnCaseStudyLink();

        assertThat(cookiesConsentBarPage.getCookiesConsentBar().isDisplayed()).isTrue();
    }

    @TmsLink("ID5517")
    @Severity(SeverityLevel.MINOR)
    @Test()
    @Description("Verify that button labels in cookies consent bar are displayed correctly")
    public void verifyConsentBarButtonLabels() {
        assertThat(cookiesConsentBarPage.getTextFromConsentBarButtons()).usingRecursiveComparison()
                .isEqualTo(CookiesConstants.CONSENT_BAR_BUTTON_LABELS);
    }

    @TmsLink("ID5518")
    @Severity(SeverityLevel.MINOR)
    @Test()
    @Description("Verify that headers in cookies consent bar and preference modal are displayed correctly")
    public void verifyCookieNotificationHeaders() {
        assertThat(cookiesConsentBarPage.getCookiesConsentBarHeader().getText())
                .isEqualTo(CookiesConstants.CONSENT_BAR_HEADER_TEXT);

        cookiesConsentBarPage.clickCustomizeButton();
        cookiesCustomizationPage = new CookiesCustomizationPage();

        assertThat(cookiesCustomizationPage.getCookiePreferenceModalHeader().getText())
                .isEqualTo(CookiesConstants.PREFERENCE_HEADER_TEXT);
    }

    @TmsLink("ID5519")
    @Severity(SeverityLevel.MINOR)
    @Test()
    @Description("Verify that labels in cookies preference modal are displayed correctly")
    public void verifyCookiePreferenceModalLabels() {
        cookiesConsentBarPage.clickCustomizeButton();
        cookiesCustomizationPage = new CookiesCustomizationPage();

        assertThat(cookiesCustomizationPage.getTextFromPreferenceModalButtons()).usingRecursiveComparison()
                .isEqualTo(CookiesConstants.PREFERENCE_BUTTON_LABELS);

        assertThat(cookiesCustomizationPage.getTextFromAccordionButtons()).usingRecursiveComparison()
                .isEqualTo(CookiesConstants.PREFERENCE_ACCORDION_BTN_LABELS);
    }
}
