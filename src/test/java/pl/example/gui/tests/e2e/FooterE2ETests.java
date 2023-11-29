package pl.example.gui.tests.e2e;

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

    @Test
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
    
    @Test
    public void checkingFooterDisplayingInSubsites() {
        FooterConstants.FOOTER_SUBSITES_LINKS.forEach((link) -> {
            PageUrl.goToPage(link);
            assertThat(footerPage.checkIfFooterIsDisplayed()).isTrue();
            assertThat(footerPage.getHeadingFromColumnsInFooter()).usingRecursiveComparison().isEqualTo(
                    FooterConstants.FOOTER_COLUMNS_HEADINGS_POLISH
            );
        });
    }
}
