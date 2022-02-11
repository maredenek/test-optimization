package org.template.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.template.AbstractPage;
import org.template.components.AddAddressForm;
import org.template.components.AddressTile;

import java.util.List;
import java.util.stream.Collectors;

public class MyAdressesPage extends AbstractPage {

    @FindBy(css = "a[title='Add an address']")
    private WebElement addNewAddressButton;

    public MyAdressesPage(WebDriver driver) {
        super(driver);
    }

    public List<AddressTile> getAddresses() {
        return driver.findElements(By.cssSelector("div.address"))
                .stream()
                .map(webElement -> new AddressTile(driver, webElement))
                .collect(Collectors.toList());
    }

    public AddAddressForm openAddingAddressForm() {
        addNewAddressButton.click();
        return new AddAddressForm(driver);
    }
}
