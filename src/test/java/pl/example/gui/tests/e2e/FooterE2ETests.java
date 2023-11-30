package pl.example.gui.tests.e2e;

import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.example.commons.FooterConstants;
import pl.example.gui.driver.browser.PageUrl;
import pl.example.gui.pages.FooterPage;
import pl.example.gui.tests.BaseTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FooterE2ETests extends BaseTest {
    FooterPage footerPage;

    @BeforeMethod
    public void beforeFooterTest() {
        footerPage = new FooterPage();
        footerPage.initializeMaps();
    }

    @Issue("FOOTER-E2E-BUG-1")
    @TmsLink("FOOTER-E2E-1")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "TEST FOOTER-E2E-1: Checking changing language")
    @Description("This test checks in switching to each language works correct")
    public void checkingChangingLanguage() {
        footerPage.clickOnButtonForSwitchingLanguage("English");
        assertThat(footerPage.getCurrentURL()).isEqualTo(FooterConstants.SITE_ADDRESS_EN);
        assertThat(footerPage.getHeadingFromColumnsInFooter()).usingRecursiveComparison().isEqualTo(
                FooterConstants.FOOTER_COLUMNS_HEADINGS_ENGLISH
        );

        footerPage.clickOnButtonForSwitchingLanguage("German");
        assertThat(footerPage.getCurrentURL()).isEqualTo(FooterConstants.SITE_ADDRESS_DE);
        assertThat(footerPage.getHeadingFromColumnsInFooter()).usingRecursiveComparison().isEqualTo(
                FooterConstants.FOOTER_COLUMNS_HEADINGS_GERMAN
        );

        footerPage.clickOnButtonForSwitchingLanguage("Polish");
        assertThat(footerPage.getCurrentURL()).isEqualTo(FooterConstants.SITE_ADDRESS_PL);
        assertThat(footerPage.getHeadingFromColumnsInFooter()).usingRecursiveComparison().isEqualTo(
                FooterConstants.FOOTER_COLUMNS_HEADINGS_POLISH
        );
    }

    @Issue("FOOTER-E2E-BUG-2")
    @TmsLink("FOOTER-E2E-2")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "TEST FOOTER-E2E-2: Checking footer displaying in subsites")
    @Description("This test checks if the footer is displayed in all subsites")
    public void checkingFooterDisplayingInSubsites() {
        FooterConstants.FOOTER_SUBSITES_LINKS.forEach((link) -> {
            PageUrl.goToPage(link);
            assertThat(footerPage.checkIfFooterIsDisplayed()).isTrue();
            assertThat(footerPage.checkIfLogoImageInFooterIsDisplayedOrNot()).isTrue();
            assertThat(footerPage.getHeadingFromColumnsInFooter()).usingRecursiveComparison().isEqualTo(
                    FooterConstants.FOOTER_COLUMNS_HEADINGS_POLISH
            );
            assertThat(footerPage.getLinkFromElementInFooter("privacyPolicyLink")).isEqualTo(
                    FooterConstants.FOOTER_PRIVACY_POLICY_LINK
            );
            assertThat(footerPage.getLinkFromElementInFooter("cookiesPolicyLink")).isEqualTo(
                    FooterConstants.FOOTER_COOKIES_POLICY_LINK
            );
        });
    }
}
