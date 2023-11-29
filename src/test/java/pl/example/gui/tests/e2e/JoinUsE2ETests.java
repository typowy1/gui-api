package pl.example.gui.tests.e2e;

import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.example.gui.pages.JoinUsPage;
import pl.example.gui.pages.TopMenuPage;
import pl.example.gui.tests.BaseTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JoinUsE2ETests extends BaseTest {

    JoinUsPage joinUsPage;
    TopMenuPage topMenuPage;

    @BeforeMethod
    public void goToJoinUsPage() {
        topMenuPage = new TopMenuPage();
        topMenuPage.clickOnJoinUsLink();
    }

    @TmsLink("ID6850")
    @Severity(SeverityLevel.BLOCKER)
    @Test()
    @Description("Check if the search field input and filters align with resulting job offers")
    public void areThereJobOffersWithProvidedTextAndFiltersTest() {
        joinUsPage = new JoinUsPage();
        joinUsPage.typeIntoSearchField("test automation");
        joinUsPage.chooseValueInLocationSelect("Warszawa");
        joinUsPage.chooseValueInLocationSelect("Kraków");
        joinUsPage.chooseValueInFieldSelect("Testing");
        joinUsPage.chooseValueInContractSelect("Umowa współpracy - B2B");
        joinUsPage.chooseValueInLevelSelect("Specjalista");
        joinUsPage.chooseValueInWorkingHoursSelect("Pełny wymiar czasu");

        assertThat(joinUsPage.getJobOffersTitlesList().size()).isGreaterThan(0);
    }

    @Issue("Bug 60")
    @TmsLink("ID6851")
    @Severity(SeverityLevel.TRIVIAL)
    @Test()
    @Description("Check if the search field input and filters align with resulting job offers failed test")
    public void areJobOffersWithProvidedTextAndFiltersSortedFailedTest() {
        joinUsPage = new JoinUsPage();
        joinUsPage.typeIntoSearchField("software developer");
        joinUsPage.chooseValueInLocationSelect("Warszawa");
        joinUsPage.chooseValueInLocationSelect("Kraków");
        joinUsPage.chooseValueInFieldSelect("Software Development");
        joinUsPage.chooseValueInContractSelect("Umowa współpracy - B2B");
        joinUsPage.chooseValueInLevelSelect("Specjalista");
        joinUsPage.chooseValueInWorkingHoursSelect("Pełny wymiar czasu");
        joinUsPage.clickRemoteWorkCheckbox();
        joinUsPage.chooseValueInSortingSelect("Alfabetycznie (A - Z)");

        List<String> sortedAndFilteredResults = joinUsPage.getJobOffersTitlesList();
        sortedAndFilteredResults.sort(String.CASE_INSENSITIVE_ORDER);

        assertThat(joinUsPage.getJobOffersTitlesList()).isNotEqualTo(sortedAndFilteredResults);
    }

    @TmsLink("ID6852")
    @Severity(SeverityLevel.CRITICAL)
    @Test()
    @Description("Check if the search field input and filters align with resulting job offers")
    public void areJobOffersClearedAfterSearchingWithProvidedTextFiltersAndSortingTest() {
        joinUsPage = new JoinUsPage();
        List<String> defaultResults = joinUsPage.getJobOffersTitlesList();
        joinUsPage.typeIntoSearchField("software developer");
        joinUsPage.chooseValueInLocationSelect("Warszawa");
        joinUsPage.chooseValueInLocationSelect("Kraków");
        joinUsPage.chooseValueInFieldSelect("Software Development");
        joinUsPage.chooseValueInContractSelect("Umowa współpracy - B2B");
        joinUsPage.chooseValueInLevelSelect("Specjalista");
        joinUsPage.chooseValueInWorkingHoursSelect("Pełny wymiar czasu");
        joinUsPage.clickRemoteWorkCheckbox();
        joinUsPage.chooseValueInSortingSelect("Alfabetycznie (A - Z)");

        List<String> sortedAndFilteredResults = joinUsPage.getJobOffersTitlesList();
        sortedAndFilteredResults.sort(String.CASE_INSENSITIVE_ORDER);

        assertThat(joinUsPage.getJobOffersTitlesList()).isEqualTo(sortedAndFilteredResults);

        joinUsPage.clickClearAllButton();

        assertThat(joinUsPage.getJobOffersTitlesList()).isEqualTo(defaultResults);
    }
}
