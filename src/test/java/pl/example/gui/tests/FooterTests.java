package pl.example.gui.tests;

import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.example.Commons.FooterConstants;
import pl.example.gui.pages.FooterPage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FooterTests extends BaseTest {
    FooterPage footerPage;

    @BeforeMethod
    public void beforeFooterTest() {
        footerPage = new FooterPage();
        footerPage.initializeMaps();
    }

    @Issue("FOOTER-BUG-0")
    @TmsLink("FOOTER-0")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @Description("Check if footer page is displayed")
    public void isFooterPageDisplayed() {
        assertThat(footerPage.checkIfFooterIsDisplayed()).isTrue();
    }

    @Issue("FOOTER-BUG-1")
    @TmsLink("FOOTER-1")
    @Severity(SeverityLevel.NORMAL)
    @Test
    @Description("Check if the address in the footer is correct")
    public void isAddressInFooterCorrect() {
        assertThat(footerPage.getTextFromAddressInFooter()).isEqualTo(
                FooterConstants.FOOTER_ADDRESS
        );
    }

    @Issue("FOOTER-BUG-2")
    @TmsLink("FOOTER-2")
    @Severity(SeverityLevel.NORMAL)
    @Test
    @Description("Check if the text of link to careers in the footer is correct")
    public void isTextLinkInCarrierInFooterCorrect() {
        assertThat(footerPage.getTextFromElementInFooter("careerLink")).isEqualTo(
                FooterConstants.FOOTER_CAREER_TEXT
        );
    }

    @Issue("FOOTER-BUG-3")
    @TmsLink("FOOTER-3")
    @Severity(SeverityLevel.MINOR)
    @Test
    @Description("Check if the link to careers in the footer is correct")
    public void isHrefLinkInCarrierInFooterCorrect() {
        assertThat(footerPage.getLinkFromElementInFooter("careerLink")).contains(
                FooterConstants.FOOTER_CAREER_LINK
        );
    }

    @Issue("FOOTER-BUG-4")
    @TmsLink("FOOTER-4")
    @Severity(SeverityLevel.MINOR)
    @Test
    @Description("Check if the changing language to English in the footer works correct")
    public void doesChangeLanguageToEnglishInFooterWorksCorrect() {
        footerPage.clickOnButtonForSwitchingLanguage("English");
        assertThat(footerPage.getCurrentURL()).isEqualTo(FooterConstants.SITE_ADDRESS_EN);
        assertThat(footerPage.getHeadingFromColumnsInFooter()).usingRecursiveComparison().isEqualTo(
                FooterConstants.FOOTER_COLUMNS_HEADINGS_ENGLISH
        );
    }

    @Issue("FOOTER-BUG-5")
    @TmsLink("FOOTER-5")
    @Severity(SeverityLevel.MINOR)
    @Test
    @Description("Check if the changing language to German in the footer works correct")
    public void doesChangeLanguageToGermanInFooterWorksCorrect() {
        footerPage.clickOnButtonForSwitchingLanguage("German");
        assertThat(footerPage.getCurrentURL()).isEqualTo(FooterConstants.SITE_ADDRESS_DE);
        assertThat(footerPage.getHeadingFromColumnsInFooter()).usingRecursiveComparison().isEqualTo(
                FooterConstants.FOOTER_COLUMNS_HEADINGS_GERMAN
        );
    }

    @Issue("FOOTER-BUG-6")
    @TmsLink("FOOTER-6")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Description("Check if the columns headers in the footer are correct")
    public void areHeadingColumnsInFooterCorrect() {
        assertThat(footerPage.getHeadingFromColumnsInFooter()).usingRecursiveComparison().isEqualTo(
                FooterConstants.FOOTER_COLUMNS_HEADINGS_POLISH
        );
    }

    @Issue("FOOTER-BUG-7")
    @TmsLink("FOOTER-7")
    @Severity(SeverityLevel.TRIVIAL)
    @Test
    @Description("Check if the links to social media in the footer are correct")
    public void areSocialMediaLinkInFooterCorrect() {
        assertThat(footerPage.getLinksToSocialMediaFromFooter()).usingRecursiveComparison().isEqualTo(
                FooterConstants.FOOTER_SOCIAL_MEDIA_LINKS
        );
    }

    @Issue("FOOTER-BUG-8")
    @TmsLink("FOOTER-8")
    @Severity(SeverityLevel.MINOR)
    @Test
    @Description("Check if the text link to privacy policy in the footer is correct")
    public void isTheTextLinkToPrivacyPolicyInTheFooterIsCorrect() {
        assertThat(footerPage.getTextFromElementInFooter("privacyPolicyLink")).isEqualTo(
                FooterConstants.FOOTER_PRIVACY_POLICY_TEXT
        );
    }

    @Issue("FOOTER-BUG-9")
    @TmsLink("FOOTER-9")
    @Severity(SeverityLevel.MINOR)
    @Test
    @Description("Check if the link to privacy policy in the footer is correct")
    public void isTheLinkToPrivacyPolicyInTheFooterIsCorrect() {
        assertThat(footerPage.getLinkFromElementInFooter("privacyPolicyLink")).isEqualTo(
                FooterConstants.FOOTER_PRIVACY_POLICY_LINK
        );
    }

    @Issue("FOOTER-BUG-10")
    @TmsLink("FOOTER-10")
    @Severity(SeverityLevel.MINOR)
    @Test
    @Description("Check if the text link to cookies policy in the footer is correct")
    public void isTheTextLinkToCookiesPolicyInTheFooterIsCorrect() {
        assertThat(footerPage.getTextFromElementInFooter("cookiesPolicyLink")).isEqualTo(
                FooterConstants.FOOTER_COOKIES_POLICY_TEXT
        );
    }

    @Issue("FOOTER-BUG-11")
    @TmsLink("FOOTER-11")
    @Severity(SeverityLevel.MINOR)
    @Test
    @Description("Check if the link to cookies policy in the footer is correct")
    public void isTheLinkToCookiesPolicyInTheFooterIsCorrect() {
        assertThat(footerPage.getLinkFromElementInFooter("cookiesPolicyLink")).isEqualTo(
                FooterConstants.FOOTER_COOKIES_POLICY_LINK
        );
    }

    @Issue("FOOTER-BUG-12")
    @TmsLink("FOOTER-12")
    @Severity(SeverityLevel.MINOR)
    @Test
    @Description("Check if the contact phone and email in the footer are correct")
    public void isTheContactPhoneAndEmailInTheFooterAreCorrect() {
        assertThat(footerPage.getTextFromContactPhoneAndEmailInFooter()).isEqualTo(
                FooterConstants.FOOTER_PHONE_AND_EMAIL
        );
    }

    @Issue("FOOTER-BUG-13")
    @TmsLink("FOOTER-13")
    @Severity(SeverityLevel.MINOR)
    @Test
    @Description("Check if logo in footer is displayed")
    public void isLogoInFooterDisplayed() {
        assertThat(footerPage.checkIfLogoImageInFooterIsDisplayedOrNot()).isTrue();
        assertThat(footerPage.getLogoSourceLinkInFooter()).isEqualTo(
                FooterConstants.FOOTER_LOGO_IMAGE_SRC
        );
    }

    @Issue("FOOTER-BUG-14")
    @TmsLink("FOOTER-14")
    @Severity(SeverityLevel.MINOR)
    @Test(testName = "Check if Copyright year in footer is actual")
    @Description("Check if Copyright year in footer is actual")
    public void isCopyrightYearInFooterActual() {
        assertThat(footerPage.checkIfCopyrightYearInFooterIsActual()).isTrue();
    }
}
