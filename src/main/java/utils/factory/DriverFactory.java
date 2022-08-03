package utils.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import utils.Config;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;


public class DriverFactory {

    private final static Logger LOGGER = LogManager.getLogger(DriverFactory.class);
    private static WebDriver webDriver = null;
    public static boolean isWebDriverRun = false;

    private DriverFactory() {
    }

    public static WebDriver getWebDriver() {
        return webDriver;
    }

    public static void prepareChromeDriver() {
        LOGGER.info("Prepare the chrome driver");
        WebDriverManager.getInstance(CHROME).setup();
    }

    public static void createWebInstance() throws MalformedURLException {
        createWebInstance(Config.ENV.RUN_LOCATION());
    }

    public static void createWebInstance(String location) throws MalformedURLException {
        isWebDriverRun = true;
        Run _location = Run.fromText(location);
        LOGGER.info(String.format("Creating Web Instance on %s", _location));
        if (webDriver != null) {
            return;
        }
        switch (_location) {
            case LOCAL:
                ChromeOptions options = new ChromeOptions();
                options.setPageLoadStrategy(PageLoadStrategy.NONE);
                webDriver = new ChromeDriver(options);
                webDriver.manage().timeouts().implicitlyWait(Config.ENV.TIME_OUTS(), TimeUnit.SECONDS);
                webDriver.manage().timeouts().pageLoadTimeout(Config.ENV.TIME_OUTS(), TimeUnit.SECONDS);
                webDriver.manage().timeouts().setScriptTimeout(Config.ENV.TIME_OUTS(), TimeUnit.SECONDS);
                break;
            default:
                break;
        }
    }

    public static void dismissInstances() {
        if (webDriver != null) {
            LOGGER.info("Dismissing Web instance");
            webDriver.quit();
            webDriver = null;
            isWebDriverRun = false;
        }
    }

    public static void initPages(String packageName, WebDriver driver) {
        LOGGER.info("Initialize page");
        try {
            Class[] pageClasses = getClasses(packageName);
            for (Class pageClass : pageClasses
            ) {
                PageFactory.initElements(driver, pageClass);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    private static Class[] getClasses(String packageName)
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration resources = classLoader.getResources(path);
        List dirs = new ArrayList();
        while (resources.hasMoreElements()) {
            URL resource = (URL) resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList classes = new ArrayList();
        for (Object directory : dirs) {
            classes.addAll(findClasses((File) directory, packageName));
        }
        return (Class[]) classes.toArray(new Class[classes.size()]);
    }

    private static List findClasses(File directory, String packageName) throws ClassNotFoundException {
        List classes = new ArrayList();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}


