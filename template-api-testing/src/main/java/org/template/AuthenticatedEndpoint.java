package org.template;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Objects;
import java.util.function.Predicate;

@Setter
@Accessors(chain = true)
public abstract class AuthenticatedEndpoint<E, M> extends BaseEndpoint<E, M> {

    private String sessionCookieName;
    private String sessionCookieValue;

    public AuthenticatedEndpoint(String baseUri, String basePath, Predicate<Response> responseChecker) {
        super(baseUri, basePath, responseChecker);
    }

    @Override
    protected RequestSpecification baseSpec() {
        Objects.requireNonNull(sessionCookieName, "session cookie name has not been set");
        Objects.requireNonNull(sessionCookieValue, "session cookie value has not been set");
        return super.baseSpec()
                .cookie(sessionCookieName, sessionCookieValue);
    }

}
