package pl.example.gui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.example.gui.commonMethods.CommonMethods;
import pl.example.gui.waits.Waits;

import java.util.ArrayList;
import java.util.List;

public class JoinUsPage extends BasePage {

    private static final By JOB_OFFERS_LOCATOR = By.cssSelector(".job-search-results-tile");
    private static final By JOB_OFFERS_TITLES_LOCATOR = By.cssSelector(".job-search-results-tile .job-search-results-tile__basic_info__title");

    @FindBy(css = "div.job-search-box__title")
    private WebElement headingH1;

    @FindBy(css = "div.layout-header__container__logo")
    private WebElement logoImage;

    @FindBy(name = "search")
    private WebElement searchInput;

    @FindBy(css = "#mat-select-4")
    private WebElement locationSelect;

    @FindBy(css = "#mat-select-4-panel mat-option span")
    private List<WebElement> locationOptions;

    @FindBy(css = "#mat-select-6")
    private WebElement fieldSelect;

    @FindBy(css = "#mat-select-6-panel mat-option span")
    private List<WebElement> fieldOptions;

    @FindBy(css = "#mat-select-8")
    private WebElement contractSelect;

    @FindBy(css = "#mat-select-8-panel mat-option span")
    private List<WebElement> contractOptions;

    @FindBy(css = "#mat-select-10")
    private WebElement levelSelect;

    @FindBy(css = "#mat-select-10-panel mat-option span")
    private List<WebElement> levelOptions;

    @FindBy(css = "#mat-select-12")
    private WebElement workingHoursSelect;

    @FindBy(css = "#mat-select-12-panel mat-option span")
    private List<WebElement> workingHoursOptions;

    @FindBy(css = "#job-search-results mat-select")
    private WebElement sortingSelect;

    @FindBy(css = ".cdk-overlay-container mat-option > span")
    private List<WebElement> sortingOptions;

    @FindBy(css = ".job-search-box__filters .mat-button")
    private WebElement clearAllButton;

    @FindBy(css = "#mat-checkbox-2-input")
    private WebElement remoteWorkCheckBox;


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

    @Step("Choose an option in location select")
    public void chooseValueInLocationSelect(String location) {
        CommonMethods.clickOnElement(locationSelect);
        log().info("Clicked on location select");
        WebElement locationElement = CommonMethods.findElementFromListByText(locationOptions, location);
        CommonMethods.clickOnElement(locationElement);
        log().info("Clicked on option in location select: {}", location);
        CommonMethods.refocusOnTheSite();
    }

    @Step("Choose an option in field select")
    public void chooseValueInFieldSelect(String field) {
        CommonMethods.clickOnElement(fieldSelect);
        log().info("Clicked on field select");
        WebElement fieldElement = CommonMethods.findElementFromListByText(fieldOptions, field);
        CommonMethods.clickOnElement(fieldElement);
        log().info("Clicked on option in location select: {}", field);
        CommonMethods.refocusOnTheSite();
    }

    @Step("Choose an option in contract select")
    public void chooseValueInContractSelect(String contract) {
        CommonMethods.clickOnElement(contractSelect);
        log().info("Clicked on contract select");
        WebElement contractElement = CommonMethods.findElementFromListByText(contractOptions, contract);
        CommonMethods.clickOnElement(contractElement);
        log().info("Clicked on option in contract select: {}", contract);
        CommonMethods.refocusOnTheSite();
    }

    @Step("Choose an option in level select")
    public void chooseValueInLevelSelect(String level) {
        CommonMethods.clickOnElement(levelSelect);
        log().info("Clicked on level select");
        WebElement levelElement = CommonMethods.findElementFromListByText(levelOptions, level);
        CommonMethods.clickOnElement(levelElement);
        log().info("Clicked on option in level select: {}", level);
        CommonMethods.refocusOnTheSite();
    }

    @Step("Choose an option in level select")
    public void chooseValueInWorkingHoursSelect(String workingHours) {
        CommonMethods.clickOnElement(workingHoursSelect);
        log().info("Clicked on working hours select");
        WebElement levelElement = CommonMethods.findElementFromListByText(workingHoursOptions, workingHours);
        CommonMethods.clickOnElement(levelElement);
        log().info("Clicked on option in working hours select: {}", workingHours);
        CommonMethods.refocusOnTheSite();
    }

    @Step("Choose an option in sorting select")
    public void chooseValueInSortingSelect(String sorting) {
        //must wait for list to be FULLY reloaded, to avoid glitches
        Waits.sleep(1000);
        CommonMethods.clickOnElement(sortingSelect);
        log().info("Clicked on sorting select");
        WebElement sortingElement = CommonMethods.findElementFromListByText(sortingOptions, sorting);
        CommonMethods.clickOnElement(sortingElement);
        log().info("Clicked on option in sorting select: {}", sorting);
        CommonMethods.refocusOnTheSite();
    }

    @Step("Click remote work checkbox")
    public void clickRemoteWorkCheckbox() {
        CommonMethods.clickCheckBoxUsingJS(remoteWorkCheckBox);
        log().info("Clicked remote work checbkox");
    }

    @Step("Scroll to job offers")
    public void scrollToJobOffers() {
        List<WebElement> refreshedJobOffers = Waits.waitIfStaleElementsListReferenceException(JOB_OFFERS_LOCATOR);
        if (!refreshedJobOffers.isEmpty()) {
            CommonMethods.scrollToElement(refreshedJobOffers.get(0));
            log().info("Scrolled to: " + refreshedJobOffers.get(0));
        }
    }

    @Step("Click clear all button")
    public void clickClearAllButton() {
        CommonMethods.clickOnElement(clearAllButton);
        log().info("Clicked clear all button");
    }

    @Step("Type into search field")
    public void typeIntoSearchField(String text) {
        CommonMethods.typeIntoElement(searchInput, text);
    }

    @Step("Get job offers titles texts list")
    public List<String> getJobOffersTitlesList() {
        Waits.sleep(1000);
        List<String> jobOffersTextsList = new ArrayList<>();
        List<WebElement> resultsTitles = Waits.waitIfStaleElementsListReferenceException(JOB_OFFERS_TITLES_LOCATOR);
        for (WebElement jobOffer : resultsTitles) {
            jobOffersTextsList.add(CommonMethods.getTextFromElementNoColor(jobOffer));
        }
        return jobOffersTextsList;
    }
}