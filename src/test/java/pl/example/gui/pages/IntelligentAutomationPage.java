package pl.example.gui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.example.gui.commonMethods.CommonMethods;

public class IntelligentAutomationPage extends BasePage {

    @FindBy(css = ".et_pb_text_0 .et_pb_text_inner h1")
    private WebElement headingH1;

    @Step("Getting text from heading H1")
    public String getTextFromHeadingH1() {
        String headingH1Text = CommonMethods.getTextFromElement(headingH1);
        log().info("Returned heading text was: {}", headingH1Text);
        return headingH1Text;
    }
}
