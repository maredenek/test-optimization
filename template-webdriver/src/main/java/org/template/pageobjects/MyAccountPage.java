package org.template.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.template.AbstractPage;

public class MyAccountPage extends AbstractPage {

    @FindBy(css = "a[title=Orders]")
    private WebElement orderHistoryButton;
    @FindBy(css = "a[title='Credit slips']")
    private WebElement creditSlipsButton;
    @FindBy(css = "a[title=Addresses]")
    private WebElement myAdressesButton;
    @FindBy(css = "a[title=Information]")
    private WebElement myPersonalInfoButton;
    @FindBy(css = "a[title='My wishlists']")
    private WebElement myWishListButton;

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    public MyAdressesPage openMyAdresses() {
        myAdressesButton.click();
        return new MyAdressesPage(driver);
    }

}
