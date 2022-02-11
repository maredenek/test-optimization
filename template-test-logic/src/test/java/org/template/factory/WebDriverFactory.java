package org.template.factory;

import com.google.common.collect.ImmutableMap;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.template.configuration.Browser;
import org.template.configuration.Configuration;

import java.time.Duration;
import java.util.Map;
import java.util.function.Supplier;

public class WebDriverFactory {

    private static WebDriver webDriver;
    private static final Map<Browser, Supplier<WebDriver>> webDriversSuppliers = ImmutableMap.of(
            Browser.CHROME, getChromeWebDriver(),
            Browser.FIREFOX, getFirefoxWebDriver(),
            Browser.IE, getIEWebDriver(),
            Browser.SAFARI, getSafariWebDriver()
    );

    public static WebDriver getWebDriver() {
        if(webDriver == null) {
            Browser browser = Configuration.BROWSER;
            webDriver = webDriversSuppliers.get(browser).get();
        }
        return webDriver;
    }

    public static void quitWebDriver() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }

    private static <T extends WebDriver> T configure(final T driver) {
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        return driver;
    }

    private static Supplier<WebDriver> getChromeWebDriver() {
        WebDriverManager.chromedriver().setup();
        return () -> configure(new ChromeDriver());
    }

    private static Supplier<WebDriver> getFirefoxWebDriver() {
        WebDriverManager.firefoxdriver().setup();
        return () -> configure(new FirefoxDriver());
    }

    private static Supplier<WebDriver> getIEWebDriver() {
        WebDriverManager.iedriver().setup();
        return () -> configure(new InternetExplorerDriver());
    }

    private static Supplier<WebDriver> getSafariWebDriver() {
        WebDriverManager.safaridriver().setup();
        return () -> configure(new SafariDriver());
    }



}
