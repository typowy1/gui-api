package pl.example.gui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.example.gui.commonMethods.CommonMethods;

public class JoinUsPage extends BasePage {

    @FindBy(css = "div.job-search-box__title")
    private WebElement headingH1;

    @FindBy(css = "div.layout-header__container__logo")
    private WebElement logoImage;


    @Step("Getting text from heading H1")
    public String getTextFromHeadingH1() {
        String headingH1Text = CommonMethods.getTextFromElement(headingH1);
        log().info("Returned heading text was: {}", headingH1Text);
        return headingH1Text;
    }

    @Step("Click on Logo Image")
    public void clickOnLogoImage() {
        CommonMethods.clickOnElement(logoImage);
        log().info("Clicked on Logo Image");
    }
}
