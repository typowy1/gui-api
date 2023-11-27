package pl.example.gui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.example.Commons.FooterConstants;
import pl.example.gui.commonMethods.CommonMethods;

public class HomePage extends BasePage {

    @FindBy(css = "rs-layer#slider-4-slide-12-layer-0")
    private WebElement headingH1;

    @Step("Getting text from heading H1")
    public String getTextFromHeadingH1() {
        String headingH1Text = CommonMethods.getTextFromElement(headingH1);
        log().info("Returned heading text was: {}", headingH1Text);
        return headingH1Text;
    }

    @Step("Check if current website version is in polish")
    public void isCurrentWebsiteVersionInPolish() {
        String currentUrl = CommonMethods.getCurrentUrlString();
        if (!currentUrl.equals(FooterConstants.SITE_ADDRESS_PL)) {
            log().info("Website is in english version");
            FooterPage footerPage = new FooterPage();
            footerPage.initializeMaps();
            footerPage.clickOnButtonForSwitchingLanguage("Polish");
            CommonMethods.scrollToTop();
            log().info("Scrolled to top of the page");
        } else log().info("Website is in polish version");
    }
}
