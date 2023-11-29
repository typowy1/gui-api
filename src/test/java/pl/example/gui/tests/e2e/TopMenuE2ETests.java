package pl.example.gui.tests.e2e;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;
import pl.example.gui.pages.*;
import pl.example.gui.tests.BaseTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TopMenuE2ETests extends BaseTest {

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

    @TmsLink("ID628")
    @Severity(SeverityLevel.CRITICAL)
    @Test()
    @Description("Check if every Top Menu tab will return the correct page")
    public void isEveryTopMenuLinkClickable() {
        topMenuPage = new TopMenuPage();
        topMenuPage.clickOnAboutUsLink();

        aboutUsPage = new AboutUsPage();
        assertThat(aboutUsPage.getTextFromHeadingH1()).isEqualTo("WE DELIVER\nYOUR IDEAS");

        topMenuPage.clickOnLogoImage();

        homePage = new HomePage();
        assertThat(homePage.getTextFromHeadingH1()).isEqualTo("ROZWIĄZANIA\nCLOUD");

        topMenuPage.clickOnOfferLink();

        offerPage = new OfferPage();
        assertThat(offerPage.getTextFromHeadingH1()).isEqualTo("POZNAJ NASZĄ OFERTĘ");

        topMenuPage.clickOnCaseStudyLink();

        caseStudyPage = new CaseStudyPage();
        assertThat(caseStudyPage.getTextFromHeadingH1()).isEqualTo("CASE\nSTUDY");

        topMenuPage.clickOnCareerLink();

        careerPage = new CareerPage();
        assertThat(careerPage.getTextFromHeadingH1()).isEqualTo("KARIERA");

        topMenuPage.clickOnContactLink();

        contactPage = new ContactPage();
        assertThat(contactPage.getTextFromHeadingH1()).isEqualTo("KONTAKT");

        topMenuPage.clickOnJoinUsLink();

        joinUsPage = new JoinUsPage();
        assertThat(joinUsPage.getTextFromHeadingH1()).isEqualTo("Znajdź swoją \nidealną pracę");
        joinUsPage.clickOnLogoImage();

        homePage.isCurrentWebsiteVersionInPolish();
        assertThat(homePage.getTextFromHeadingH1()).isEqualTo("ROZWIĄZANIA\nCLOUD");

        topMenuPage.hoverOverOfferLink();
        topMenuPage.clickOnElementInOffer("Cloud Transformation Services");

        cloudTransformationServicesPage = new CloudTransformationServicesPage();
        assertThat(cloudTransformationServicesPage.getTextFromHeadingH1()).isEqualTo("CLOUD TRANSFORMATION SERVICES");

        topMenuPage.hoverOverOfferLink();
        topMenuPage.clickOnElementInOffer("Rozwój Oprogramowania");

        softwareDevelopmentPage = new SoftwareDevelopmentPage();
        assertThat(softwareDevelopmentPage.getTextFromHeadingH1()).isEqualTo("ROZWÓJ OPROGRAMOWANIA");

        topMenuPage.hoverOverOfferLink();
        topMenuPage.clickOnElementInOffer("Data & Analytics");

        dataAndAnalyticsPage = new DataAndAnalyticsPage();
        assertThat(dataAndAnalyticsPage.getTextFromHeadingH1()).isEqualTo("DATA & ANALYTICS");

        topMenuPage.hoverOverOfferLink();
        topMenuPage.clickOnElementInOffer("Inteligentna Automatyzacja");

        intelligentAutomationPage = new IntelligentAutomationPage();
        assertThat(intelligentAutomationPage.getTextFromHeadingH1()).isEqualTo("INTELIGENTNA AUTOMATYZACJA");

        topMenuPage.hoverOverOfferLink();
        topMenuPage.clickOnElementInOffer("Cloud Managed Services");

        cloudManagedServicesPage = new CloudManagedServicesPage();
        assertThat(cloudManagedServicesPage.getTextFromHeadingH1()).isEqualTo("CLOUD MANAGED SERVICES");

        topMenuPage.hoverOverOfferLink();
        topMenuPage.clickOnElementInOffer("Cyber Security");

        cyberSecurityPage = new CyberSecurityPage();
        assertThat(cyberSecurityPage.getTextFromHeadingH1()).isEqualTo("CYBER\nSECURITY");

        topMenuPage.hoverOverOfferLink();
        topMenuPage.clickOnElementInOffer("Rozwiązania Finansowe");

        financialSolutionsPage = new FinancialSolutionsPage();
        assertThat(financialSolutionsPage.getTextFromHeadingH1()).isEqualTo("ROZWIĄZANIA FINANSOWE");

        topMenuPage.hoverOverOfferLink();
        topMenuPage.clickOnElementInOffer("Test Factory Center");

        testFactoryCenterPage = new TestFactoryCenterPage();
        assertThat(testFactoryCenterPage.getTextFromHeadingH1()).isEqualTo("TEST FACTORY CENTER");

        topMenuPage.hoverOverOfferLink();
        topMenuPage.clickOnElementInOffer("Rozwiązania Mobilne");

        mobileSolutionsPage = new MobileSolutionsPage();
        assertThat(mobileSolutionsPage.getTextFromHeadingH1()).isEqualTo("ROZWIĄZANIA MOBILNE");
    }
}
