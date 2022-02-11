package org.template.pageobjects;

import org.openqa.selenium.WebDriver;
import org.template.AbstractPage;
import org.template.components.LoginForm;
import org.template.components.RegisterForm;

public class LoginPage extends AbstractPage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginForm loginForm() {
        return new LoginForm(driver);
    }

    public RegisterForm registerForm() {
        return new RegisterForm(driver);
    }

}
