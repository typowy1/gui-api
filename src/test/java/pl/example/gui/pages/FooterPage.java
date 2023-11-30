package pl.example.gui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import pl.example.gui.commonMethods.CommonMethods;

import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FooterPage extends BasePage {

    @FindBy(css = "footer")
    private WebElement footerElement;

    @FindBy(css = ".et_pb_column_1_tb_footer p:nth-child(1)")
    @CacheLookup
    private WebElement addressInFooter;

    @FindBy(css = ".et_pb_column_1_tb_footer p:nth-child(2)")
    @CacheLookup
    private WebElement contactInFooter;

    @FindBy(css = ".et_pb_column_3_tb_footer p:nth-child(1) a")
    @CacheLookup
    private WebElement careerLinkInFooter;

    @FindBy(css = ".footer-copyrights .polylang_langswitcher")
    private WebElement footerLangSwitcher;

    @FindBy(css = ".footer-copyrights .polylang_langswitcher .lang-item-pl")
    @CacheLookup
    private WebElement footerLangSwitcherToPolish;

    @FindBy(css = ".footer-copyrights .polylang_langswitcher .lang-item-en")
    @CacheLookup
    private WebElement footerLangSwitcherToEnglish;

    @FindBy(css = ".footer-copyrights .polylang_langswitcher .lang-item-de")
    @CacheLookup
    private WebElement footerLangSwitcherToGerman;

    @FindBy(css = ".cky-notice-btn-wrapper .cky-btn.cky-btn-reject")
    @CacheLookup
    private WebElement rejectCookiesButton;

    @FindBy(css = "footer .et_pb_row_0_tb_footer h4")
    private List<WebElement> footerColumnsHeadingsList;

    @FindBy(css = "footer .et_pb_row_0_tb_footer li a")
    @CacheLookup
    private List<WebElement> footerSocialMediaLinks;

    @FindBy(css = ".footer-copyrights .et_pb_text_16_tb_footer a")
    private WebElement footerCopyrightPrivacyPolicyLink;

    @FindBy(css = ".footer-copyrights .et_pb_text_15_tb_footer a")
    private WebElement footerCopyrightCookiesPolicyLink;

    @FindBy(css = ".et_pb_text_17_tb_footer")
    @CacheLookup
    private WebElement footerCopyrightText;

    @FindBy(css = ".et_pb_row_0_tb_footer img")
    private WebElement footerLogo;

    private static final Map<String, WebElement> languageOptions = new HashMap<>();
    private static final Map<String, WebElement> stringWebElementMap = new HashMap<>();

    public void initializeMaps() {
        languageOptions.put("English", footerLangSwitcherToEnglish);
        languageOptions.put("German", footerLangSwitcherToGerman);
        languageOptions.put("Polish", footerLangSwitcherToPolish);

        stringWebElementMap.put("careerLink", careerLinkInFooter);
        stringWebElementMap.put("privacyPolicyLink", footerCopyrightPrivacyPolicyLink);
        stringWebElementMap.put("cookiesPolicyLink", footerCopyrightCookiesPolicyLink);
    }

    @Step("Getting current page URL")
    public String getCurrentURL() {
        String currentURL = CommonMethods.getCurrentUrlString();
        log().info("Returned current page URL is: {}", currentURL);
        return currentURL;
    }

    @Step("Checking if footer is displayed")
    public boolean checkIfFooterIsDisplayed() {
        CommonMethods.scrollToElement(footerElement);
        boolean isFooterDisplayed = CommonMethods.checkIfElementIsDisplayed(footerElement);
        String resultString = isFooterDisplayed ? "is" : "is NOT";
        log().info("Footer page {} displayed", resultString);
        return isFooterDisplayed;
    }

    @Step("Getting text from address in footer")
    public String getTextFromAddressInFooter() {
        CommonMethods.scrollToElement(addressInFooter);
        String headingH1Text = CommonMethods.getTextFromElement(addressInFooter);
        log().info("Returned warning text was: {}", headingH1Text);
        return headingH1Text;
    }

    @Step("Getting text from contact phone and email in footer")
    public String getTextFromContactPhoneAndEmailInFooter() {
        CommonMethods.scrollToElement(contactInFooter);
        String phoneAndEmailText = CommonMethods.getTextFromElement(contactInFooter);
        log().info("Returned warning text was: {}", phoneAndEmailText.replace("\n",", "));
        return phoneAndEmailText;
    }

    @Step("Getting text from element")
    public String getTextFromElementInFooter(String targetElement) {
        CommonMethods.scrollToElement(stringWebElementMap.get(targetElement));
        String textLink = CommonMethods.getTextFromElement(stringWebElementMap.get(targetElement));
        log().info("Returned text link was: {}", textLink);
        return textLink;
    }

    @Step("Getting link from element")
    public String getLinkFromElementInFooter(String targetElement) {
        CommonMethods.scrollToElement(stringWebElementMap.get(targetElement));
        String textLink = CommonMethods.getHrefFromElement(stringWebElementMap.get(targetElement));
        log().info("Returned href link was: {}", textLink);
        return textLink;
    }

    @Step("Click on language switcher in footer")
    public void clickOnLanguageSwitcherInFooter() {
        CommonMethods.scrollToElement(footerLangSwitcher);
        CommonMethods.clickOnElement(footerLangSwitcher);
        log().info("Clicked on language switcher in footer");
    }

    @Step("Click on button for switching language in footer")
    public void clickOnButtonForSwitchingLanguage(String lang) {
        clickOnLanguageSwitcherInFooter();
        CommonMethods.clickOnElement(languageOptions.get(lang));
        log().info("Clicked on button for switching language to: " + lang);
    }

    @Step("Reject and close Cookies banner")
    public void rejectAndCloseCookieBanner() {
        CommonMethods.clickOnElement(rejectCookiesButton);
        log().info("Clicked on reject cookies button and close cookies banner");
    }

    @Step("Getting headings from columns in footer")
    public List<String> getHeadingFromColumnsInFooter() {
        CommonMethods.scrollToElement(footerLogo);
        List<String> footerColumnsHeadersListString = CommonMethods.getTextValuesFromWebElementsList(footerColumnsHeadingsList);
        log().info("Returned columns headers list was: {}", footerColumnsHeadersListString);
        return footerColumnsHeadersListString;
    }

    @Step("Getting links to social media from footer")
    public List<String> getLinksToSocialMediaFromFooter() {
        CommonMethods.scrollToElement(footerSocialMediaLinks.get(0));
        List<String> footerSocialMediaLinksListString =footerSocialMediaLinks.stream().map(CommonMethods::getHrefFromElement).collect(Collectors.toList());
        log().info("Returned columns headers list was: {}", footerSocialMediaLinksListString);
        return footerSocialMediaLinksListString;
    }

    @Step("Checking if logo image in footer is displayed or not")
    public boolean checkIfLogoImageInFooterIsDisplayedOrNot() {
        CommonMethods.scrollToElement(footerLogo);
        boolean isLogoDisplayed = CommonMethods.checkIfElementIsDisplayed(footerLogo);
        log().info("State of displaying logo image in footer is: {}", isLogoDisplayed);
        return isLogoDisplayed;
    }

    @Step("Getting source link to logo image in footer")
    public String getLogoSourceLinkInFooter() {
        CommonMethods.scrollToElement(footerLogo);
        String gotLinkSrc = CommonMethods.getDomAttributeFromElement(footerLogo, "src");
        log().info("Returned image logo source link was: {}", gotLinkSrc);
        return gotLinkSrc;
    }

    @Step("Checking if year in Copyright in footer is actual")
    public boolean checkIfCopyrightYearInFooterIsActual() {
        CommonMethods.scrollToElement(footerCopyrightText);
        boolean isActualYear = CommonMethods.getTextFromElement(footerCopyrightText).contains(Year.now().toString());
        String resultString = isActualYear ? "is" : "is NOT";
        log().info("Year in Copyright {} as actual year", resultString);
        return isActualYear;
    }
}
