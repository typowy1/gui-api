package pl.example.gui.tests.e2e;

import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pl.example.gui.commonMethods.CommonMethods;
import pl.example.gui.driver.browser.Browser;
import pl.example.gui.driver.browser.BrowserUtils;
import pl.example.gui.driver.browser.PageUrl;
import pl.example.gui.driver.manager.DriverSetup;
import pl.example.gui.pages.CookiePolicyPage;
import pl.example.gui.pages.CookiesConsentBarPage;
import pl.example.gui.pages.CookiesCustomizationPage;
import pl.example.gui.tests.BaseTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CookiePreferenceE2ETests extends BaseTest {

    CookiesConsentBarPage cookiesConsentBarPage;

    CookiesCustomizationPage cookiesCustomizationPage;

    CookiePolicyPage cookiePolicyPage;

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

    @TmsLink("ID6517")
    @Severity(SeverityLevel.NORMAL)
    @Test()
    @Description("E2E test path for cookies customization")
    public void cookiesCustomizationPath() {
        cookiesConsentBarPage.clickCustomizeButton();
        cookiesCustomizationPage = new CookiesCustomizationPage();

        cookiesCustomizationPage.clickPreferencesToggle();
        assertThat(cookiesCustomizationPage.getPreferencesToggle().getAttribute("aria-label"))
                .isEqualTo("Wyłączyć Preferencyjne");

        cookiesCustomizationPage.clickPreferencesToggle();
        assertThat(cookiesCustomizationPage.getPreferencesToggle().getAttribute("aria-label"))
                .isEqualTo("Włączyć Preferencyjne");

        cookiesCustomizationPage.closeCookiePreferencesModal();
        cookiesConsentBarPage.clickCustomizeButton();

        cookiesCustomizationPage.clickPerformanceToggle();
        cookiesCustomizationPage.clickAcceptAllButton();

        assertThat(cookiesConsentBarPage.getCookiesConsentBar().isDisplayed()).isFalse();
        assertThat(cookiesCustomizationPage.getCookiePreferenceModal().isDisplayed()).isFalse();
    }

    @Issue("Bug 521")
    @TmsLink("ID6518")
    @Severity(SeverityLevel.NORMAL)
    @Test()
    @Description("E2E test path for cookies customization")
    public void cookiesCustomizationPathFailedTest() {
        cookiesConsentBarPage.clickCustomizeButton();
        cookiesCustomizationPage = new CookiesCustomizationPage();

        cookiesCustomizationPage.clickPreferencesToggle();
        assertThat(cookiesCustomizationPage.getPreferencesToggle().getAttribute("aria-label"))
                .isEqualTo("Włączyć Preferencyjne");

        cookiesCustomizationPage.clickPreferencesToggle();
        assertThat(cookiesCustomizationPage.getPreferencesToggle().getAttribute("aria-label"))
                .isEqualTo("Wyłączyć Preferencyjne");

        cookiesCustomizationPage.closeCookiePreferencesModal();
        cookiesConsentBarPage.clickCustomizeButton();
        cookiesCustomizationPage.clickPerformanceToggle();
        cookiesCustomizationPage.clickAcceptAllButton();

        assertThat(cookiesConsentBarPage.getCookiesConsentBar().isDisplayed()).isFalse();
        assertThat(cookiesCustomizationPage.getCookiePreferenceModal().isDisplayed()).isFalse();
    }

    @TmsLink("ID6519")
    @Severity(SeverityLevel.NORMAL)
    @Test()
    @Description("E2E test path for cookies consent bar")
    public void cookiesConsentBarPath() {
        CommonMethods.refreshPage();

        assertThat(cookiesConsentBarPage.getCookiesConsentBar().isDisplayed()).isTrue();
        assertThat((cookiesConsentBarPage.getNoticeTitle().getText()))
                .isEqualTo("Strona korzysta z plików cookie");

        cookiesConsentBarPage.clickCookiePolicyLink();
        cookiePolicyPage = new CookiePolicyPage();
        CommonMethods.switchToNewTab();

        assertThat(cookiePolicyPage.getCookiePolicyDocument().getAttribute("type"))
                .isEqualTo("application/pdf");
        assertThat(cookiePolicyPage.getCookiePolicyDocument().isDisplayed()).isTrue();
    }

    @Issue("Bug 242")
    @TmsLink("ID6520")
    @Severity(SeverityLevel.NORMAL)
    @Test()
    @Description("E2E test path for cookies consent bar")
    public void cookiesConsentBarPathFailedTest() {
        assertThat(cookiesConsentBarPage.getCookiesConsentBar().isDisplayed()).isTrue();
        assertThat((cookiesConsentBarPage.getNoticeTitle().getText()))
                .isEqualTo("Strona korzysta z plików cookie");

        cookiesConsentBarPage.clickCustomizeButton();
        cookiesCustomizationPage = new CookiesCustomizationPage();
        CommonMethods.refreshPage();

        cookiesConsentBarPage.clickCustomizeButton();
        cookiesCustomizationPage.closeCookiePreferencesModal();
        cookiesConsentBarPage.clickRejectAllButton();

        assertThat(cookiesConsentBarPage.getCookiesConsentBar().isDisplayed()).isTrue();
    }
}
