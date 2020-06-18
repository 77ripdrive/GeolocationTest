package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Utils {

    public Map setCoordinates(double latitude, double longitude) {
        return Map.of(
                "latitude", latitude,
                "longitude", longitude,
                "accuracy", 1
        );
    }

    public void getScreenshot(By by, String country, WebDriver driver) throws IOException {
        WebElement allProvider = driver.findElement(by);
        File file = allProvider.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("screenshots/provider" + country + ".png"));
    }

    public WebElement waitForElementVisibility(WebDriver driver, int timeoutSeconds, By by) {
        return new WebDriverWait(driver, timeoutSeconds)
                .until(ExpectedConditions.visibilityOf(driver.findElement(by)));
    }

    public void screenScroll(WebDriver driver) {
        JavascriptExecutor jse = ((JavascriptExecutor) driver);
        jse.executeScript("window.scrollTo(0, 400)");
    }

    public void setProvider(Actions actions, WebDriver driver, By allProviders, By provider) {
        actions.moveToElement(driver.findElement(allProviders))
                .moveToElement(driver.findElement(provider))
                .click().build().perform();
    }
}
