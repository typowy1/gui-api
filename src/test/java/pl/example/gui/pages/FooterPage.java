package pl.example.gui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.example.gui.commonMethods.CommonMethods;

public class FooterPage extends BasePage {
    @FindBy(css = ".et_pb_column_1_tb_footer p:nth-child(1)")
    private WebElement addressInFooter;

    @Step("Getting text from address in footer")
    public String getTextFromAddressInFooter() {
        CommonMethods.scrollToElement(addressInFooter);
        String headingH1Text = CommonMethods.getTextFromElement(addressInFooter);
        log().info("Returned warning text was: {}", headingH1Text);
        return headingH1Text;
    }
}
