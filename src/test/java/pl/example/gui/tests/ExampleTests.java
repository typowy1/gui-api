package pl.example.gui.tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import pl.example.gui.pages.AboutUsPage;
import pl.example.gui.pages.FooterPage;
import pl.example.gui.pages.TopMenuPage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ExampleTests extends BaseTest {

    TopMenuPage topMenuPage;
    AboutUsPage aboutUsPage;
    FooterPage footerPage;

//    @Issue("Bug 1") //id defektu, przekieruje do defektu
//    @TmsLink("ID333") // id przypadku testowego, przekieruje do testu
//    @Severity(SeverityLevel.BLOCKER)
//    @Test()
//    @Description("Check if the About As tab will return the correct page")
//    public void isAboutUsLinkClickable() {
//        topMenuPage = new TopMenuPage();
//        topMenuPage.clickOnAboutUsLink();
//
//        aboutUsPage = new AboutUsPage();
//        assertThat(aboutUsPage.getTextFromHeadingH1()).isEqualTo("WE DELIVER\n" + "YOUR IDEAS");
//    }

    @Issue("Bug 2") //id defektu, przekieruje do defektu
    @TmsLink("ID444") // id przypadku testowego, przekieruje do testu
    @Severity(SeverityLevel.BLOCKER)
    @Test()
    @Description("Check if the About As tab will return the correct page")
    public void isAboutUsLinkClickableFailedTest() {
        topMenuPage = new TopMenuPage();
        topMenuPage.clickOnAboutUsLink();
        aboutUsPage = new AboutUsPage();
        assertThat(aboutUsPage.getTextFromHeadingH1()).isEqualTo("WE DELIVER");
    }

    @Issue("Bug 3") //id defektu, przekieruje do defektu
    @TmsLink("ID567") // id przypadku testowego, przekieruje do testu
    @Severity(SeverityLevel.MINOR)
    @Test()
    @Description("Check if the address in the footer is correct")
    public void isAddressInFooterCorrect() {
        footerPage = new FooterPage();
        assertThat(footerPage.getTextFromAddressInFooter()).isEqualTo
                ("Onwelo Sp. z o.o.\n" +
                        "ul. Prosta 67\n" +
                        "00-838 Warszawa");
    }
//
//    @Issue("Bug 4") //id defektu, przekieruje do defektu
//    @TmsLink("ID5655") // id przypadku testowego, przekieruje do testu
//    @Severity(SeverityLevel.MINOR)
//    @Test()
//    @Description("Check if the address in the footer is correct")
//    public void isAddressInFooterCorrectFailedTest() {
//        footerPage = new FooterPage();
//        assertThat(footerPage.getTextFromAddressInFooter()).isEqualTo
//                ("00-838 Warszawa");
//    }
}
