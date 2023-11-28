package pl.example.gui.commonMethods;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import pl.example.gui.driver.manager.DriverSetup;
import pl.example.gui.waits.Waits;

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

    public static void scrollToTop() {
        javascriptExecutor = (JavascriptExecutor) DriverSetup.getWebDriver();
        javascriptExecutor.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
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

    public static void clickOnElementUsingJS(WebElement element) {
        javascriptExecutor = (JavascriptExecutor) DriverSetup.getWebDriver();
        markElementWithColor(element);
        javascriptExecutor.executeScript("arguments[0].click();", element);
    }

    public static void clickOnToggleElement(WebElement toggle) {
        String currentToggleState = toggle.getAttribute("aria-label");
        Waits.waitUntilElementIsClickable(toggle);
        markElementWithColor(toggle);
        CommonMethods.clickOnElement(toggle);

        if (currentToggleState.contains("Wyłączyć")) {
            Waits.waitForDomAttributeToContain(toggle, "aria-label", "Włączyć");
        } else {
            Waits.waitForDomAttributeToContain(toggle, "aria-label", "Wyłączyć");
        }
    }

    public static String getTextFromElement(WebElement element) {
        Waits.waitUntilElementIsVisible(element);
        CommonMethods.markElementWithColor(element);
        return element.getText().trim();
    }

    public static void hoverOverElement(WebElement element) {
        Waits.waitUntilElementIsVisible(element);
        CommonMethods.markElementWithColor(element);

        Actions actions = new Actions(DriverSetup.getWebDriver());
        actions.moveToElement(element).perform();
    }

    public static String getCurrentUrlString() {
        return DriverSetup.getWebDriver().getCurrentUrl();
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

    public static void refreshPage() {
        DriverSetup.getWebDriver().navigate().refresh();
    }

    public static void switchToNewTab() {
        WebDriver driver = DriverSetup.getWebDriver();
        String originalWindow = driver.getWindowHandle();

        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }


}
