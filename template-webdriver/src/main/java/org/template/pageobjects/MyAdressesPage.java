package org.template.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.template.AbstractPage;
import org.template.components.AddressTile;

import java.util.List;
import java.util.stream.Collectors;

public class MyAdressesPage extends AbstractPage {

    public MyAdressesPage(WebDriver driver) {
        super(driver);
    }

    public List<AddressTile> getAddresses() {
        return driver.findElements(By.cssSelector("div.address"))
                .stream()
                .map(webElement -> new AddressTile(driver, webElement))
                .collect(Collectors.toList());
    }
}
