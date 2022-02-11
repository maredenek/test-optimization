package org.template;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.function.Predicate;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.RequiredArgsConstructor;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@RequiredArgsConstructor
public abstract class BaseEndpoint<E, M> {

    protected final String baseUri;
    protected final String basePath;
    protected final Predicate<Response> responseChecker;
    protected Response response;


    protected abstract Type getModelType();

    public abstract E sendRequest();

    protected RequestSpecification baseSpec() {
        return given()
                .baseUri(baseUri)
                .basePath(basePath);
    }

    protected void assertThatResponseIsNotNull() {
        assertThat(response, is(notNullValue()));
    }

    public Map<String, String> getResponseCookies() {
        return response.getCookies();
    }

    public M getResponseModel() {
        assertThatResponseIsNotNull();
        return response.as(getModelType());
    }

    public E assertRequestSuccess() {
        assertThatResponseIsNotNull();
        assertThat(responseChecker.test(response), is(true));
        return (E) this;
    }

}
