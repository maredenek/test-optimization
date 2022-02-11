package org.template.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.template.AbstractPage;

import java.util.Map;

public class AddAddressForm extends AbstractPage {

    @FindBy(css = "input#firstname")
    private WebElement firstNameInput;
    @FindBy(css = "input#lastname")
    private WebElement lastNameInput;
    @FindBy(css = "input#address1")
    private WebElement addressInput;
    @FindBy(css = "input#city")
    private WebElement cityInput;
    @FindBy(css = "select#id_state")
    private WebElement stateWebElement;
    @FindBy(css = "input#postcode")
    private WebElement postCodeInput;
    @FindBy(css = "select#id_country")
    private WebElement countryWebElement;
    @FindBy(css = "input#phone")
    private WebElement phoneInput;
    @FindBy(css = "input#alias")
    private WebElement aliasInput;
    @FindBy(css = "button#submitAddress")
    private WebElement submitButton;

    public AddAddressForm(WebDriver driver) {
        super(driver);
    }

    public void fillAndSendFormUsing(Map<String, String> data) {
        Select stateSelect = new Select(stateWebElement);
        Select countrySelect = new Select(countryWebElement);
        firstNameInput.clear();
        firstNameInput.sendKeys(data.get("firstname"));
        lastNameInput.clear();
        lastNameInput.sendKeys(data.get("lastname"));
        addressInput.sendKeys(data.get("address1"));
        cityInput.sendKeys(data.get("city"));
        stateSelect.selectByValue(data.get("id_state"));
        postCodeInput.sendKeys(data.get("postcode"));
        countrySelect.selectByValue(data.get("id_country"));
        phoneInput.sendKeys(data.get("phone"));
        aliasInput.clear();
        aliasInput.sendKeys(data.get("alias"));
        submitButton.click();
    }
}
