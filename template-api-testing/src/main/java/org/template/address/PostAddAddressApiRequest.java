package org.template.address;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.http.HttpStatus;
import org.template.AuthenticatedEndpoint;
import org.template.address.model.request.AddressData;

import java.lang.reflect.Type;
import java.util.Map;

@Setter
@Accessors(chain = true)
public class PostAddAddressApiRequest extends AuthenticatedEndpoint<PostAddAddressApiRequest, Object> {

    private AddressData addressData;

    public PostAddAddressApiRequest(String baseUri, String basePath) {
        super(baseUri, basePath, response -> response.getStatusCode() == HttpStatus.SC_MOVED_TEMPORARILY);
    }

    @Override
    protected Type getModelType() {
        return Object.class;
    }

    @Override
    @SuppressWarnings("unchecked")
    public PostAddAddressApiRequest sendRequest() {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> formData = mapper.convertValue(addressData, Map.class);
        response = baseSpec()
                .redirects().follow(false)
                .contentType(ContentType.URLENC)
                .formParams(formData)
                .queryParam("controller", "address")
                .post("/index.php");
        return this;
    }
}
