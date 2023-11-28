package pl.example.gui.waits;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pl.example.gui.driver.manager.DriverSetup;

import java.time.Duration;

public class Waits {

    WebDriver driver = DriverSetup.getWebDriver();

    private static WebDriverWait getWebDriverWait() {
        return new WebDriverWait(DriverSetup.getWebDriver(), Duration.ofSeconds(10), Duration.ofMillis(250));
    }

    public static void waitUntilElementIsVisible(WebElement element) {
        WebDriverWait webDriverWait = getWebDriverWait();
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitUntilElementIsClickable(WebElement element) {
        WebDriverWait webDriverWait = getWebDriverWait();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitUntilElementIsLocated(By locator) {
        WebDriverWait webDriverWait = getWebDriverWait();
        try {
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            Assert.fail();
        }
    }

    public static void waitUntilElementIsLocatedInDom(By locator) {
        WebDriverWait webDriverWait = getWebDriverWait();
        try {
            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            Assert.fail(e.getMessage());
        }
    }

    public static void waitUntilElementWillBeInvisible(WebElement element) {
        WebDriverWait webDriverWait = getWebDriverWait();
        webDriverWait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void pageLoadTimeout(int seconds) {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(seconds));
    }

    public static void waitUntilElementIsClickableAndClickOnElement(WebElement element) {
        WebDriverWait webDriverWait = getWebDriverWait();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //#### StaleElementReferenceException ####
//    Trzeba zaincjalizować jeszcze raz element, zastosowanie ponniższej metody waitIfStaleElementReferenceException przy drugim inicjalizowaniu web elementu
//    WebElement element = driver.findElement(By.id("id"));
//    element = waitIfStaleElementReferenceException(By.id("id"));

    public static WebElement waitIfStaleElementReferenceException(By by) {
        WebDriverWait webDriverWait = getWebDriverWait();
        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void fluentWaitForElementToBeClickable(WebElement element) {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(DriverSetup.getWebDriver());
        fluentWait.withTimeout(Duration.ofSeconds(5));
        fluentWait.pollingEvery(Duration.ofMillis(600));
        fluentWait.ignoring(NoSuchElementException.class);
        fluentWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void fluentWaitForElementToBeVisible(WebElement element) {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(DriverSetup.getWebDriver());
        fluentWait.withTimeout(Duration.ofSeconds(5));
        fluentWait.pollingEvery(Duration.ofMillis(600));
        fluentWait.ignoring(NoSuchElementException.class);
        fluentWait.until(ExpectedConditions.visibilityOf(element));
    }

    // Thread.sleep added because the loop is too fast - movement is not quick enough to change position of element
    // between checks
    public static void waitUntilElementStopsMoving(WebElement element) {
        WebDriverWait webDriverWait = getWebDriverWait();
        webDriverWait.until((ExpectedCondition<Boolean>) webDriver -> {
            int abs;
            int prevPositionY = element.getLocation().getY();
            do {
                sleep(50);
                abs = Math.abs(prevPositionY - element.getLocation().getY());
                prevPositionY = element.getLocation().getY();
            } while (abs > 0);
            return true;
        });
    }

    public static void waitForDomAttributeToBe(WebElement element, String attribute, String value) {
        WebDriverWait webDriverWait = getWebDriverWait();
        webDriverWait.until(ExpectedConditions.domAttributeToBe(element, attribute, value));
        webDriverWait.until((ExpectedConditions.attributeContains(element, attribute, value)));
    }

    public static void waitForDomAttributeToContain(WebElement element, String attribute, String value) {
        WebDriverWait webDriverWait = getWebDriverWait();
        webDriverWait.until(ExpectedConditions.attributeContains(element, attribute, value));
    }
}
