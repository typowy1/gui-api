package pl.example.gui.tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import pl.example.gui.pages.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TopMenuTests extends BaseTest {

    AboutUsPage aboutUsPage;
    TopMenuPage topMenuPage;
    OfferPage offerPage;
    CaseStudyPage caseStudyPage;
    CareerPage careerPage;
    ContactPage contactPage;
    HomePage homePage;
    CloudTransformationServicesPage cloudTransformationServicesPage;
    SoftwareDevelopmentPage softwareDevelopmentPage;
    CloudManagedServicesPage cloudManagedServicesPage;
    CyberSecurityPage cyberSecurityPage;
    DataAndAnalyticsPage dataAndAnalyticsPage;
    FinancialSolutionsPage financialSolutionsPage;
    IntelligentAutomationPage intelligentAutomationPage;
    MobileSolutionsPage mobileSolutionsPage;
    TestFactoryCenterPage testFactoryCenterPage;
    JoinUsPage joinUsPage;


    @TmsLink("ID611")
    @Severity(SeverityLevel.CRITICAL)
    @Test()
    @Description("Check if the Offer tab will return the correct page")
    public void isOfferLinkClickable() {
        topMenuPage = new TopMenuPage();
        topMenuPage.clickOnOfferLink();

        offerPage = new OfferPage();
        assertThat(offerPage.getTextFromHeadingH1()).isEqualTo("POZNAJ NASZĄ OFERTĘ");
    }

    @TmsLink("ID612")
    @Severity(SeverityLevel.MINOR)
    @Test()
    @Description("Check if the Case Study tab will return the correct page")
    public void isCaseStudyLinkClickable() {
        topMenuPage = new TopMenuPage();
        topMenuPage.clickOnCaseStudyLink();

        caseStudyPage = new CaseStudyPage();
        assertThat(caseStudyPage.getTextFromHeadingH1()).isEqualTo("CASE\nSTUDY");
    }

    @TmsLink("ID613")
    @Severity(SeverityLevel.NORMAL)
    @Test()
    @Description("Check if the Career tab will return the correct page")
    public void isCareerLinkClickable() {
        topMenuPage = new TopMenuPage();
        topMenuPage.clickOnCareerLink();

        careerPage = new CareerPage();
        assertThat(careerPage.getTextFromHeadingH1()).isEqualTo("KARIERA");
    }

    @Issue("Bug 6")
    @TmsLink("ID614")
    @Severity(SeverityLevel.NORMAL)
    @Test()
    @Description("Check if the Career tab will return the correct page")
    public void isCareerLinkClickableFailedTest() {
        topMenuPage = new TopMenuPage();
        topMenuPage.clickOnCareerLink();

        careerPage = new CareerPage();
        assertThat(careerPage.getTextFromHeadingH1()).isEqualTo("SCIEZKA KARIERY");
    }

    @TmsLink("ID615")
    @Severity(SeverityLevel.TRIVIAL)
    @Test()
    @Description("Check if the Contact tab will return the correct page")
    public void isContactLinkClickable() {
        topMenuPage = new TopMenuPage();
        topMenuPage.clickOnContactLink();

        contactPage = new ContactPage();
        assertThat(contactPage.getTextFromHeadingH1()).isEqualTo("KONTAKT");
    }

    @Issue("Bug 5")
    @TmsLink("ID616")
    @Severity(SeverityLevel.TRIVIAL)
    @Test()
    @Description("Check if the Contact tab will return the correct page")
    public void isContactLinkClickableFailedTest() {
        topMenuPage = new TopMenuPage();
        topMenuPage.clickOnContactLink();

        contactPage = new ContactPage();
        assertThat(contactPage.getTextFromHeadingH1()).isEqualTo("KONTAKTY");
    }

    @TmsLink("ID617")
    @Severity(SeverityLevel.NORMAL)
    @Test()
    @Description("Check if the Logo Image will return the correct page")
    public void isLogoImageClickable() {
        topMenuPage = new TopMenuPage();
        topMenuPage.clickOnLogoImage();

        homePage = new HomePage();
        assertThat(homePage.getTextFromHeadingH1()).isEqualTo("ROZWIĄZANIA\nCLOUD");
    }

    @Issue("Bug 1")
    @TmsLink("ID333")
    @Severity(SeverityLevel.BLOCKER)
    @Test()
    @Description("Check if the About As tab will return the correct page")
    public void isAboutUsLinkClickable() {
        topMenuPage = new TopMenuPage();
        topMenuPage.clickOnAboutUsLink();

        aboutUsPage = new AboutUsPage();
        assertThat(aboutUsPage.getTextFromHeadingH1()).isEqualTo("WE DELIVER\nYOUR IDEAS");
    }

    @Issue("Bug 2")
    @TmsLink("ID444")
    @Severity(SeverityLevel.BLOCKER)
    @Test()
    @Description("Check if the About As tab will return the correct page")
    public void isAboutUsLinkClickableFailedTest() {
        topMenuPage = new TopMenuPage();
        topMenuPage.clickOnAboutUsLink();

        aboutUsPage = new AboutUsPage();
        assertThat(aboutUsPage.getTextFromHeadingH1()).isEqualTo("WE DELIVER");
    }

    @TmsLink("ID627")
    @Severity(SeverityLevel.NORMAL)
    @Test()
    @Description("Check if the Join Us Link will return the correct page")
    public void isJoinUsLinkClickable() {
        topMenuPage = new TopMenuPage();
        topMenuPage.clickOnJoinUsLink();

        joinUsPage = new JoinUsPage();
        assertThat(joinUsPage.getTextFromHeadingH1()).isEqualTo("Znajdź swoją \nidealną pracę");
    }

    @TmsLink("ID618")
    @Severity(SeverityLevel.TRIVIAL)
    @Test()
    @Description("Check if the Cloud Transformation Services tab will return the correct page")
    public void isCloudTransformationServicesLinkClickable() {
        topMenuPage = new TopMenuPage();
        topMenuPage.hoverOverOfferLink();
        topMenuPage.clickOnElementInOffer("Cloud Transformation Services");

        cloudTransformationServicesPage = new CloudTransformationServicesPage();
        assertThat(cloudTransformationServicesPage.getTextFromHeadingH1()).isEqualTo("CLOUD TRANSFORMATION SERVICES");
    }

    @TmsLink("ID619")
    @Severity(SeverityLevel.NORMAL)
    @Test()
    @Description("Check if the Software Development tab will return the correct page")
    public void isSoftwareDevelopmentLinkClickable() {
        topMenuPage = new TopMenuPage();
        topMenuPage.hoverOverOfferLink();
        topMenuPage.clickOnElementInOffer("Rozwój Oprogramowania");

        softwareDevelopmentPage = new SoftwareDevelopmentPage();
        assertThat(softwareDevelopmentPage.getTextFromHeadingH1()).isEqualTo("ROZWÓJ OPROGRAMOWANIA");
    }

    @TmsLink("ID620")
    @Severity(SeverityLevel.NORMAL)
    @Test()
    @Description("Check if the Data and Analytics tab will return the correct page")
    public void isDataAndAnalyticsLinkClickable() {
        topMenuPage = new TopMenuPage();
        topMenuPage.hoverOverOfferLink();
        topMenuPage.clickOnElementInOffer("Data & Analytics");

        dataAndAnalyticsPage = new DataAndAnalyticsPage();
        assertThat(dataAndAnalyticsPage.getTextFromHeadingH1()).isEqualTo("DATA & ANALYTICS");
    }

    @TmsLink("ID621")
    @Severity(SeverityLevel.NORMAL)
    @Test()
    @Description("Check if the Intelligent Automation tab will return the correct page")
    public void isIntelligentAutomationLinkClickable() {
        topMenuPage = new TopMenuPage();
        topMenuPage.hoverOverOfferLink();
        topMenuPage.clickOnElementInOffer("Inteligentna Automatyzacja");

        intelligentAutomationPage = new IntelligentAutomationPage();
        assertThat(intelligentAutomationPage.getTextFromHeadingH1()).isEqualTo("INTELIGENTNA AUTOMATYZACJA");
    }

    @TmsLink("ID622")
    @Severity(SeverityLevel.NORMAL)
    @Test()
    @Description("Check if the Cloud Managed Services tab will return the correct page")
    public void isCloudManagedServicesLinkClickable() {
        topMenuPage = new TopMenuPage();
        topMenuPage.hoverOverOfferLink();
        topMenuPage.clickOnElementInOffer("Cloud Managed Services");

        cloudManagedServicesPage = new CloudManagedServicesPage();
        assertThat(cloudManagedServicesPage.getTextFromHeadingH1()).isEqualTo("CLOUD MANAGED SERVICES");
    }

    @TmsLink("ID623")
    @Severity(SeverityLevel.NORMAL)
    @Test()
    @Description("Check if the Cyber Security tab will return the correct page")
    public void isCyberSecurityLinkClickable() {
        topMenuPage = new TopMenuPage();
        topMenuPage.hoverOverOfferLink();
        topMenuPage.clickOnElementInOffer("Cyber Security");

        cyberSecurityPage = new CyberSecurityPage();
        assertThat(cyberSecurityPage.getTextFromHeadingH1()).isEqualTo("CYBER\nSECURITY");
    }

    @TmsLink("ID624")
    @Severity(SeverityLevel.NORMAL)
    @Test()
    @Description("Check if the Financial Solutions tab will return the correct page")
    public void isFinancialSolutionsLinkClickable() {
        topMenuPage = new TopMenuPage();
        topMenuPage.hoverOverOfferLink();
        topMenuPage.clickOnElementInOffer("Rozwiązania Finansowe");

        financialSolutionsPage = new FinancialSolutionsPage();
        assertThat(financialSolutionsPage.getTextFromHeadingH1()).isEqualTo("ROZWIĄZANIA FINANSOWE");
    }

    @TmsLink("ID625")
    @Severity(SeverityLevel.NORMAL)
    @Test()
    @Description("Check if the Test Factory Center tab will return the correct page")
    public void isTestFactoryCenterLinkClickable() {
        topMenuPage = new TopMenuPage();
        topMenuPage.hoverOverOfferLink();
        topMenuPage.clickOnElementInOffer("Test Factory Center");

        testFactoryCenterPage = new TestFactoryCenterPage();
        assertThat(testFactoryCenterPage.getTextFromHeadingH1()).isEqualTo("TEST FACTORY CENTER");
    }

    @TmsLink("ID626")
    @Severity(SeverityLevel.NORMAL)
    @Test()
    @Description("Check if the Mobile Solutions tab will return the correct page")
    public void isMobileSolutionsLinkClickable() {
        topMenuPage = new TopMenuPage();
        topMenuPage.hoverOverOfferLink();
        topMenuPage.clickOnElementInOffer("Rozwiązania Mobilne");

        mobileSolutionsPage = new MobileSolutionsPage();
        assertThat(mobileSolutionsPage.getTextFromHeadingH1()).isEqualTo("ROZWIĄZANIA MOBILNE");
    }
}
