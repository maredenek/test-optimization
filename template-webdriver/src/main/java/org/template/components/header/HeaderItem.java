package org.template.components.header;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;

@RequiredArgsConstructor
public enum HeaderItem {

    SIGN_IN_BUTTON(By.xpath("//a[@class='login']")),
    SIGN_OUT_BUTTON(By.cssSelector("a.logout")),
    MY_ACCOUNT_BUTTON(By.cssSelector("div.header_user_info span"));

    @Getter
    private final By locator;

}
