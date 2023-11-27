package pl.example.gui.commonMethods;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pl.example.gui.driver.manager.DriverSetup;
import pl.example.gui.waits.Waits;
import propertiesConfig.ConfigurationProperties;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


public class CommonMethods {
    WebDriver driver = DriverSetup.getWebDriver();
    private static JavascriptExecutor javascriptExecutor;
    private static Logger logger = LogManager.getLogger(CommonMethods.class);


    public static void markElementWithColor(WebElement element) {
        javascriptExecutor = (JavascriptExecutor) DriverSetup.getWebDriver();
        javascriptExecutor.executeScript("arguments[0].style.backgroundColor = 'lightblue';", element);
    }

    public static void scrollToElement(WebElement element) {
        Waits.waitUntilElementIsVisible(element);
        javascriptExecutor = (JavascriptExecutor) DriverSetup.getWebDriver();
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void clickCheckBox(WebElement element) {
        Waits.waitUntilElementIsClickable(element);
        if (!element.isSelected()) {
            element.click();
        }
    }

    public static void clickOnElement(WebElement element) {
        Waits.waitUntilElementIsClickable(element);
        CommonMethods.markElementWithColor(element);
        element.click();
    }

    public static String getTextFromElement(WebElement element) {
        Waits.waitUntilElementIsVisible(element);
        CommonMethods.markElementWithColor(element);
        return element.getText().trim();
    }

    public static String replaceUnnecessaryNumber(WebElement element, int startFrom, int quantity) {
        String correctNumber = element.getText().replace(" ", "")
                .substring(startFrom, element.getText().length() - quantity).trim();
        return correctNumber;
    }

    public static String getHrefFromElement(WebElement element) {
        Waits.waitUntilElementIsVisible(element);
        CommonMethods.markElementWithColor(element);
        return element.getDomProperty("href").trim();
    }

    public static boolean checkIfElementIsDisplayed(WebElement element) {
        CommonMethods.markElementWithColor(element);
        return element.isDisplayed() && element.getSize().getHeight() > 0;
    }

    public static String getDomAttributeFromElement(WebElement element, String attribute) {
        Waits.waitUntilElementIsVisible(element);
        CommonMethods.markElementWithColor(element);
        return element.getDomAttribute(attribute);
    }

    public static String getCurrentUrlString() {
        return DriverSetup.getWebDriver().getCurrentUrl();
    }

    public static List<String> getTextValuesFromWebElementsList(List<WebElement> webElementList) {
        return webElementList.stream().map(element -> {
            CommonMethods.markElementWithColor(element);
            return element.getText();
        }).collect(Collectors.toList());
    }

    public List<WebElement> getElementsListByLocator(By by) {
        List<WebElement> listOfElements = driver.findElements(by);
        logger.info("Elements list size = " + listOfElements.size());
        return listOfElements;
    }

    public WebElement getRandomElement(WebElement element, By locator) {
        Waits.waitUntilElementIsVisible(element);
        List<WebElement> elementsList = driver.findElements(locator);
        Assert.assertTrue(elementsList.size() > 0, "Elements list is not found");
        logger.info("Size of the elements list: " + elementsList.size());
        Random random = new Random();
        int randomValue = random.nextInt(elementsList.size());
        WebElement rootItem = elementsList.get(randomValue);
        return rootItem;
    }
}
