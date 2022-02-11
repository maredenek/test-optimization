package org.template.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.template.AbstractPage;

public class LoginForm extends AbstractPage {

    @FindBy(id = "email")
    private WebElement emailInput;
    @FindBy(id = "passwd")
    private WebElement passwordInput;
    @FindBy(id = "SubmitLogin")
    private WebElement submitButton;
    @FindBy(xpath = "//*[@id=\"center_column\"]/div[1]/ol/li")
    private WebElement errorElement;

    public LoginForm(WebDriver driver) {
        super(driver);
    }

    public LoginForm fillAndSendLoginForm(String email, String password) {
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        submitButton.click();
        return this;
    }

    public String getErrorMessage() {
        return errorElement.getText();
    }
}
