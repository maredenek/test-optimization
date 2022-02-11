package org.template.components.header;

import org.openqa.selenium.WebDriver;
import org.template.AbstractPage;
import org.template.pageobjects.LoginPage;

public class Header extends AbstractPage {

    public Header(WebDriver driver) {
        super(driver);
    }

    public LoginPage goToLogInPage() {
        clickButton(HeaderItem.SIGN_IN_BUTTON);
        return new LoginPage(driver);
    }

    public void clickButton(HeaderItem button) {
        driver.findElement(button.getLocator()).click();
    }

    public boolean isButtonDisplayed(HeaderItem button) {
        return driver.findElement(button.getLocator()).isDisplayed();
    }

}
