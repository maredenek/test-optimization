package org.template.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.template.AbstractPage;
import org.template.components.header.Header;

public class HomePage extends AbstractPage {

    @FindBy(xpath = "//a[@class='login']")
    private WebElement singInButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public Header header() {
        return new Header(driver);
    }

}
