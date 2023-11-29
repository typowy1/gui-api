package pl.example.gui.tests;

import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.example.gui.pages.JoinUsPage;
import pl.example.gui.pages.TopMenuPage;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JoinUsTests extends BaseTest {

    JoinUsPage joinUsPage;
    TopMenuPage topMenuPage;

    @BeforeMethod
    public void goToJoinUsPage() {
        topMenuPage = new TopMenuPage();
        topMenuPage.clickOnJoinUsLink();
    }

    @TmsLink("ID6834")
    @Severity(SeverityLevel.MINOR)
    @Test()
    @Description("Check if the search field input aligns with resulting job offers")
    public void areThereJobOffersWithProvidedTextTest() {
        joinUsPage = new JoinUsPage();
        joinUsPage.typeIntoSearchField("test automation engineer");
        joinUsPage.scrollToJobOffers();

        assertThat(joinUsPage.getJobOffersTitlesList().size()).isGreaterThan(0);
    }

    @TmsLink("ID6835")
    @Severity(SeverityLevel.MINOR)
    @Test()
    @Description("Check if the job filters align with resulting job offers")
    public void areThereJobOffersWithProvidedCriteriaTest() {
        joinUsPage = new JoinUsPage();
        joinUsPage.chooseValueInLocationSelect("Warszawa");
        joinUsPage.chooseValueInFieldSelect("Testing");
        joinUsPage.chooseValueInContractSelect("Umowa współpracy - B2B");
        joinUsPage.chooseValueInLevelSelect("Specjalista");
        joinUsPage.scrollToJobOffers();

        assertThat(joinUsPage.getJobOffersTitlesList().size()).isGreaterThan(0);
    }

    @Issue("Bug 47")
    @TmsLink("ID6836")
    @Severity(SeverityLevel.MINOR)
    @Test()
    @Description("Check if the job filters align with resulting job offers failed test")
    public void areThereJobOffersWithProvidedCriteriaFailedTest() {
        joinUsPage = new JoinUsPage();
        joinUsPage.chooseValueInLocationSelect("Kraków");
        joinUsPage.chooseValueInFieldSelect("Software Development");
        joinUsPage.chooseValueInContractSelect("Umowa współpracy - B2B");
        joinUsPage.chooseValueInLevelSelect("Specjalista");
        joinUsPage.scrollToJobOffers();

        assertThat(joinUsPage.getJobOffersTitlesList().size()).isLessThan(0);
    }

    @Issue("Bug 48")
    @TmsLink("ID6837")
    @Severity(SeverityLevel.MINOR)
    @Test()
    @Description("Check if the job offers are sorted properly in natural order")
    public void areJobOffersSortedProperlyInNaturalOrder() {
        joinUsPage = new JoinUsPage();
        joinUsPage.chooseValueInSortingSelect("Alfabetycznie (A - Z)");

        List<String> jobOffersTextsList = joinUsPage.getJobOffersTitlesList();
        jobOffersTextsList.sort(String.CASE_INSENSITIVE_ORDER);

        joinUsPage.scrollToJobOffers();

        assertThat(joinUsPage.getJobOffersTitlesList()).isEqualTo(jobOffersTextsList);
    }

    @Issue("Bug 49")
    @TmsLink("ID6937")
    @Severity(SeverityLevel.MINOR)
    @Test()
    @Description("Check if the job offers are sorted properly in reversed natural order")
    public void areJobOffersSortedProperlyInReversedNaturalOrder() {
        joinUsPage = new JoinUsPage();
        joinUsPage.chooseValueInSortingSelect("Alfabetycznie (Z - A)");

        List<String> jobOffersTextsList = joinUsPage.getJobOffersTitlesList();
        jobOffersTextsList.sort(String.CASE_INSENSITIVE_ORDER.reversed());

        joinUsPage.scrollToJobOffers();

        assertThat(joinUsPage.getJobOffersTitlesList()).isEqualTo(jobOffersTextsList);
    }

    @Issue("Bug 50")
    @TmsLink("ID6838")
    @Severity(SeverityLevel.MINOR)
    @Test()
    @Description("Check if the job filters align with resulting job offers and clear")
    public void areThereJobOffersWithProvidedCriteriaAndClearTest() {
        joinUsPage = new JoinUsPage();

        List<String> jobOffersTitlesList = joinUsPage.getJobOffersTitlesList();

        joinUsPage.chooseValueInLocationSelect("Warszawa");
        joinUsPage.chooseValueInFieldSelect("Testing");
        joinUsPage.chooseValueInContractSelect("Umowa współpracy - B2B");
        joinUsPage.chooseValueInLevelSelect("Specjalista");

        assertThat(joinUsPage.getJobOffersTitlesList()).isNotEqualTo(jobOffersTitlesList);

        joinUsPage.clickClearAllButton();

        assertThat(joinUsPage.getJobOffersTitlesList()).isEqualTo(jobOffersTitlesList);
    }
}
