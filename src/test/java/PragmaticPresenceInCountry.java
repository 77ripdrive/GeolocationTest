import org.junit.jupiter.api.Assertions;import org.junit.jupiter.params.ParameterizedTest;import org.junit.jupiter.params.provider.Arguments;import org.junit.jupiter.params.provider.CsvFileSource;import org.openqa.selenium.By;import org.openqa.selenium.support.ui.ExpectedConditions;import org.slf4j.Logger;import org.slf4j.LoggerFactory;import java.io.IOException;import java.util.concurrent.TimeUnit;import java.util.stream.Stream;public class PragmaticPresenceInCountry extends BaseTest {    Logger logger = LoggerFactory.getLogger(PragmaticPresenceInCountry.class);    private By pragmaticProvider = By.xpath("//*[text()[contains(.,'Pragmatic')]]");    private By allProviders = (By.xpath("//*[text()[contains(.,'All providers')]]"));    private By actualProviderScreen = By.xpath("//*[@id='applicationContainer']/div/section[6]/div");    private String baseUrl = "https://www.vipclub.casino/en";    private final static int TIME_OUT = 10;    @ParameterizedTest    @CsvFileSource(resources = "/countryCoordinate.csv", numLinesToSkip = 1)//    @MethodSource("countryCoordinate")    public void isThereProviderInTheCountryPresence(double latitude, double longitude, String country)            throws IOException, InterruptedException {        driver.executeCdpCommand("Emulation.setGeolocationOverride",                utils.setCoordinates(latitude, longitude));        driver.navigate().to(baseUrl);        TimeUnit.SECONDS.sleep(TIME_OUT);        utils.screenScroll(driver);        utils.waitForElement(driver, TIME_OUT)                .until(ExpectedConditions.visibilityOf(driver.findElement(allProviders)));        logger.info("Main page open and allproviders visible");        actions.moveToElement(driver.findElement(allProviders))                .moveToElement(driver.findElement(pragmaticProvider))                .click().build().perform();        String actual = driver.findElement(pragmaticProvider).getText();        utils.getScreenshot(actualProviderScreen, country, driver);        Assertions.assertEquals("PRAGMATICPLAY", actual,                "PragmaticPlay present in " + country);        logger.info("PragmaticPlay present in {} ", country);    }    private static Stream<Arguments> countryCoordinate() {        return Stream.of(                Arguments.of(55.6711876, 12.4537417, "Denmark")        );    }}