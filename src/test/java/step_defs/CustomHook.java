package step_defs;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.factory.DriverFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

public class CustomHook {
    private final static Logger LOGGER = LogManager.getLogger("Cucumber");

    @Given("Open a web browser")
    @Before("@web")
    public void iOpenAWebBrowser() throws MalformedURLException {
        DriverFactory.createWebInstance();
        DriverFactory.initPages("page_objects.web", DriverFactory.getWebDriver());
    }

    @After()
    public void afterEachScenario(Scenario scenarioResult) {
        try {
            testDataEmbeddedOnFail(scenarioResult);
            screenshotOnFail(scenarioResult);
        } catch (Exception e) {
            LOGGER.error("Unable to take screenshot");
        } finally {
            DriverFactory.dismissInstances();
        }
    }

    public byte[] shootPage() throws IOException {
        BufferedImage image = Shutterbug.shootPage(DriverFactory.getWebDriver(), ScrollStrategy.BOTH_DIRECTIONS).getImage();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", outputStream);
        return outputStream.toByteArray();
    }

    private void testDataEmbeddedOnFail(Scenario scenario) {
        String url = "";
        if (DriverFactory.isWebDriverRun) {
            url = "\nURL: " + DriverFactory.getWebDriver().getCurrentUrl();
        }
        scenario.write("TEST DATA USED:\n" + "{YOUR CUSTOM OUTPUT}" + url);
    }

    private void screenshotOnFail(Scenario scenarioResult) {
        takeScreenshot(scenarioResult);
        String yourCustomOutput = "";
        LOGGER.error(String.format("The Test was failed with following test data: %s", yourCustomOutput));
        LOGGER.info(String.format("Test result: %s", scenarioResult.getStatus()));
    }

    private void takeScreenshot(Scenario scenario) {
        if (DriverFactory.isWebDriverRun) {
            byte[] webss = new byte[0];
            try {
                webss = shootPage();
            } catch (IOException e) {
                e.printStackTrace();
            }
            scenario.embed(webss, "image/png");
        }
    }
}
