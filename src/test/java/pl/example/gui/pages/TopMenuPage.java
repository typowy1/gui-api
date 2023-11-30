package pl.example.gui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.example.gui.commonMethods.CommonMethods;

import java.util.List;

public class TopMenuPage extends BasePage {

    @FindBy(css = "ul#top-menu .menu-item-11035")
    private WebElement aboutUsLink;

    @FindBy(css = "ul#top-menu .menu-item-4001")
    private WebElement offerLink;

    @FindBy(css = "ul#top-menu .menu-item-7372")
    private WebElement caseStudyLink;

    @FindBy(css = "ul#top-menu .menu-item-4966")
    private WebElement careerLink;

    @FindBy(css = "ul#top-menu .menu-item-5586")
    private WebElement contactLink;

    @FindBy(css = "ul#top-menu .menu-item-18038")
    private WebElement joinUsLink;

    @FindBy(css = "div.logo_container img")
    private WebElement logoImage;

    @FindBy(css = "ul#top-menu .menu-item-4001 li")
    private List<WebElement> listOfOfferLinks;

    @Step("Click on About Us Link")
    public void clickOnAboutUsLink() {
        CommonMethods.clickOnElement(aboutUsLink);
        log().info("Clicked on About Us Link");
    }

    @Step("Click on Offer Link")
    public void clickOnOfferLink() {
        CommonMethods.clickOnElement(offerLink);
        log().info("Clicked on Offer Link");
    }

    @Step("Click on Case Study Link")
    public void clickOnCaseStudyLink() {
        CommonMethods.clickOnElement(caseStudyLink);
        log().info("Clicked on Case Study Link");
    }

    @Step("Click on Career Link")
    public void clickOnCareerLink() {
        CommonMethods.clickOnElement(careerLink);
        log().info("Clicked on Career Link");
    }

    @Step("Click on Contact Link")
    public void clickOnContactLink() {
        CommonMethods.clickOnElement(contactLink);
        log().info("Clicked on Contact Link");
    }

    @Step("Click on Join Us Link")
    public void clickOnJoinUsLink() {
        CommonMethods.clickOnElement(joinUsLink);
        log().info("Clicked on Join Us Link");
    }

    @Step("Click on Logo Image")
    public void clickOnLogoImage() {
        CommonMethods.clickOnElement(logoImage);
        log().info("Clicked on Logo Image");
    }

    @Step("Hover over Offer Link")
    public void hoverOverOfferLink() {
        CommonMethods.hoverOverElement(offerLink);
        log().info("Hovered over Offer Link");
    }

    @Step("Click on link in Offer Dropdown")
    public void clickOnElementInOffer(String tab) {
        int index;
        switch (tab) {
            case "Cloud Transformation Services":
                index = 0;
                break;
            case "Rozwój Oprogramowania":
                index = 1;
                break;
            case "Data & Analytics":
                index = 2;
                break;
            case "Inteligentna Automatyzacja":
                index = 3;
                break;
            case "Cloud Managed Services":
                index = 4;
                break;
            case "Cyber Security":
                index = 5;
                break;
            case "Rozwiązania Finansowe":
                index = 6;
                break;
            case "Test Factory Center":
                index = 7;
                break;
            case "Rozwiązania Mobilne":
                index = 8;
                break;
            default:
                throw new IllegalArgumentException("Invalid tab: " + tab);
        }
        CommonMethods.clickOnElement(listOfOfferLinks.get(index));
        log().info("Clicked on " + tab + " Link");
    }
}
