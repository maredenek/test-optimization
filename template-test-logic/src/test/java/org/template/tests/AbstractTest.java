package org.template.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.template.configuration.TestData;
import org.template.factory.WebDriverFactory;
import org.template.model.User;
import org.template.pageobjects.HomePage;
import org.template.pageobjects.MyAccountPage;
import org.template.pageobjects.MyAdressesPage;

public abstract class AbstractTest {

    protected final WebDriver driver = WebDriverFactory.getWebDriver();
    protected final TestData testData = new TestData();
    protected final HomePage homePage = new HomePage(driver);
    protected final MyAccountPage myAccountPage = new MyAccountPage(driver);
    protected final MyAdressesPage myAdressesPage = new MyAdressesPage(driver);

    @BeforeEach
    public void openHomePage() {
        navigateToThePage(getBaseUrl());
    }

    @AfterEach
    public void tearDown() {
        WebDriverFactory.quitWebDriver();
    }

    protected void addSessionCookieToBrowserOf(User user) {
        driver.manage().deleteAllCookies();
        driver.manage().addCookie(user.getSessionCookie());
        driver.navigate().refresh();
    }

    protected void navigateToThePage(String url) {
        driver.get(url);
    }

    protected String getBaseUrl() {
        return testData.getEnvironmentData().getUrl();
    }

}
