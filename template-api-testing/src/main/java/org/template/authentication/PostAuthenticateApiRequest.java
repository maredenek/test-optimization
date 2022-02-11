package org.template.authentication;

import io.restassured.http.ContentType;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.http.HttpStatus;
import org.template.BaseEndpoint;

import java.lang.reflect.Type;

@Setter
@Accessors(chain = true)
public class PostAuthenticateApiRequest extends BaseEndpoint<PostAuthenticateApiRequest, Object> {

    private String email;
    private String password;

    public PostAuthenticateApiRequest(String baseUri, String basePath) {
        super(baseUri, basePath, response -> response.getStatusCode() == HttpStatus.SC_MOVED_TEMPORARILY);
    }

    @Override
    protected Type getModelType() {
        return Object.class;
    }

    @Override
    public PostAuthenticateApiRequest sendRequest() {
        response = baseSpec()
                .redirects().follow(false)
                .contentType(ContentType.URLENC)
                .formParam("email", email)
                .formParam("passwd", password)
                .formParam("back", "my-account")
                .formParam("SubmitLogin", "")
                .queryParam("controller", "authentication")
                .post("/index.php");
        return this;
    }
}
