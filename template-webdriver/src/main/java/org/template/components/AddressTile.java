package org.template.components;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.template.AbstractPage;

public class AddressTile extends AbstractPage {

    private final WebElement parent;
    private final By deleteButtonLocator = By.cssSelector("a[title=Delete]");

    public AddressTile(WebDriver driver, WebElement parent) {
        super(driver);
        this.parent = parent;
    }

    public String getAlias() {
        return parent.findElement(By.cssSelector("h3")).getText();
    }

    public void delete() {
        parent.findElement(deleteButtonLocator).click();
        driver.switchTo().alert().accept();
    }

}
