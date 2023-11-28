package pl.example.gui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.example.gui.driver.manager.DriverSetup;

public class CookiePolicyPage extends BasePage {

    @FindBy(css = "embed")
    private WebElement cookiePolicyDocument;

    public WebElement getCookiePolicyDocument() {
        return cookiePolicyDocument;
    }

    public void switchToPdfViewer() {
        WebDriver driver = DriverSetup.getWebDriver();
        driver.switchTo().frame(cookiePolicyDocument);
        // TODO: raczej do usunięcia
    }

}
