package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Utils {

    public void setEmulationCoordinates(ChromeDriver driver, double latitude, double longitude) {
        Map coordinates = Map.of(
                "latitude", latitude,
                "longitude", longitude,
                "accuracy", 1
        );
        driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
    }

    public void openMainPage(ChromeDriver driver, String baseUrl, int timeOut) throws InterruptedException {
        driver.navigate().to(baseUrl);
        TimeUnit.SECONDS.sleep(timeOut);
    }

    public void getScreenshot(By by, String country, ChromeDriver driver) throws IOException {
        WebElement allProvider = driver.findElement(by);
        File file = allProvider.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("screenshots/provider" + country + ".png"));
    }

    public WebElement waitForElementVisibility(ChromeDriver driver, int timeoutSeconds, By by) {
        return new WebDriverWait(driver, timeoutSeconds)
                .until(ExpectedConditions.visibilityOf(driver.findElement(by)));
    }

    public void screenScroll(ChromeDriver driver) {
        JavascriptExecutor jse = ((JavascriptExecutor) driver);
        jse.executeScript("window.scrollTo(0, 400)");
    }

    public void setProvider(Actions actions, WebDriver driver, By allProviders, By provider) {
        actions.moveToElement(driver.findElement(allProviders))
                .moveToElement(driver.findElement(provider))
                .click().build().perform();
    }
}
