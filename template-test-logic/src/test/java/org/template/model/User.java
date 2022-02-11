package org.template.model;

import io.vavr.Lazy;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openqa.selenium.Cookie;

@Data
@NoArgsConstructor
public class User {

    private String email;
    private String password;
    private Lazy<Cookie> sessionCookie;

    public Cookie getSessionCookie() {
        return sessionCookie.get();
    }

}
