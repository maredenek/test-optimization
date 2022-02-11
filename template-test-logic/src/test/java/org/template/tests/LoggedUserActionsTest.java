package org.template.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.template.address.model.request.AddressData;
import org.template.components.AddressTile;
import org.template.components.header.HeaderItem;
import org.template.model.User;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class LoggedUserActionsTest extends AbstractTest {

    private final User user = testData.getUsersData().get(0);
    private final AddressData newAddressData = AddressData.builder()
            .firstName("NE")
            .lastName("Assigment")
            .address1("Testowa1")
            .city("Nowe P")
            .id_state(43)
            .postcode("00000")
            .id_country(21)
            .phone("123456789")
            .alias(UUID.randomUUID().toString().substring(0,30))
            .id_address(0)
            .select_address(0)
            .token("fa1aca3a90d1b2388944951a721d77cc")
            .submitAddress("")
            .build();

    @BeforeEach
    public void logUserIn() {
        homePage.header()
                .goToLogInPage()
                .loginForm()
                .fillAndSendLoginForm(user.getEmail(), user.getPassword());
    }

    @Test
    public void shouldSuccessfullyLogOutUser() {
        // when
        homePage.header().clickButton(HeaderItem.SIGN_OUT_BUTTON);

        // then
        Assertions.assertTrue(homePage.header().isButtonDisplayed(HeaderItem.SIGN_IN_BUTTON));
    }

    @Test
    public void shouldBePossibleToRemoveAddress() {
        String expectedAddressAlias = newAddressData.getAlias();
        // given
        addAddress();

        // when
        AddressTile addressToRemove = myAdressesPage
                .getAddresses()
                .stream()
                .filter(address -> expectedAddressAlias.equalsIgnoreCase(address.getAlias()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No address found with alias: " + expectedAddressAlias));
        addressToRemove.delete();

        // then
        List<String> addressesAliases = myAdressesPage
                .getAddresses()
                .stream()
                .map(AddressTile::getAlias)
                .collect(Collectors.toList());
        Assertions.assertFalse(addressesAliases.contains(expectedAddressAlias));
    }

    private void addAddress() {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> addressDataAsMap = mapper.convertValue(newAddressData, new TypeReference<>(){});
        homePage.header().clickButton(HeaderItem.MY_ACCOUNT_BUTTON);
        myAccountPage
                .openMyAdresses()
                .openAddingAddressForm()
                .fillAndSendFormUsing(addressDataAsMap);
    }
}
