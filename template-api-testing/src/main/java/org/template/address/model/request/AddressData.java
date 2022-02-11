package org.template.address.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressData {

    @JsonProperty("firstname")
    private String firstName;
    @JsonProperty("lastname")
    private String lastName;
    private String company;
    private String address1;
    private String address2;
    private String city;
    private Integer id_state;
    private String postcode;
    private Integer id_country;
    private String phone;
    private String phone_mobile;
    private String dni;
    private String other;
    private String alias;
    private Integer id_address;
    private Integer select_address;
    private String token;
    private String submitAddress;

}
