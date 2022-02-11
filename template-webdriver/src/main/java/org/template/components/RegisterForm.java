package org.template.components;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.template.AbstractPage;

public class RegisterForm extends AbstractPage {

    @FindBy(css = "input#customer_firstname")
    private WebElement inputFirstName;
    @FindBy(css = "input#customer_lastname")
    private WebElement inputLastName;
    @FindBy(css = "input#passwd")
    private WebElement inputPassword;
    @FindBy(css = "button#submitAccount")
    private WebElement buttonSubmit;
    @FindBy(css = "div.alert-danger")
    private WebElement alertContainer;
    @FindBy(css = "input#email_create")
    private WebElement inputEmail;
    @FindBy(css = "button#SubmitCreate")
    private WebElement buttonCreateAnAccount;
    @FindBy(css = "label[for=id_gender1]")
    private WebElement radioTitleMan;
    @FindBy(css = "label[for=id_gender2]")
    private WebElement radioTitleWoman;

    public RegisterForm(WebDriver driver) {
        super(driver);
    }

    public RegisterForm openRegisterFormUsing(String email) {
        inputEmail.clear();
        inputEmail.sendKeys(email);
        buttonCreateAnAccount.click();
        return this;
    }

    public RegisterForm registerUser(Gender gender, String firstName, String lastName, String password) {
        selectGenderWithoutSwitch(gender);
        inputFirstName.clear();
        inputFirstName.sendKeys(firstName);
        inputLastName.clear();
        inputLastName.sendKeys(lastName);
        inputPassword.clear();
        inputPassword.sendKeys(password);
        buttonSubmit.click();
        return this;
    }

    public boolean isErrorDisplayed() {
        try {
            return alertContainer.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isErrorDisplayedWithoutTryCatch() {
        return !driver.findElements(By.cssSelector("div.alert-danger")).isEmpty();
    }

    public RegisterForm selectGender(Gender gender) {
        switch (gender) {
            case MALE:
                radioTitleMan.click();
                break;
            case FEMALE:
                radioTitleWoman.click();
                break;
        }
        return this;
    }

    public RegisterForm selectGenderWithoutSwitch(Gender gender) {
        driver.findElement(gender.getRegisterFormLocator()).click();
        return this;
    }

}
