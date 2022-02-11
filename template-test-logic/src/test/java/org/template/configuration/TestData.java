package org.template.configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.Lazy;
import lombok.SneakyThrows;
import org.openqa.selenium.Cookie;
import org.template.authentication.PostAuthenticateApiRequest;
import org.template.model.EnvironmentData;
import org.template.model.User;

import java.net.URL;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;

public class TestData {

    private final Lazy<List<User>> usersData = Lazy.of(() -> getFileDataAsListOf(User.class, "usersData.json"));
    private final Lazy<EnvironmentData> environmentData = Lazy.of(() -> getFileDataAsListOf(EnvironmentData.class, "environmentData.json").get(0));
    private final Consumer<User> setCookieSessionFetcher = user -> user.setSessionCookie(Lazy.of(() -> fetchUserSessionCookie(user)));

    public List<User> getUsersData() {
        usersData.get().forEach(setCookieSessionFetcher);
        return usersData.get();
    }

    public EnvironmentData getEnvironmentData() {
        return environmentData.get();
    }

    @SneakyThrows
    private <T> List<T> getFileDataAsListOf(Class<T> type, String fileName) {
        String env = Configuration.ENVIRONMENT;
        ObjectMapper mapper = new ObjectMapper();
        URL url = getClass().getClassLoader().getResource(fileName);
        requireNonNull(url, "Cannot find resource file: " + fileName);
        Map<String, List<T>> envsWithData = mapper.readValue(url.openStream(), new TypeReference<>() {});
        List<T> specificEnvData = requireNonNull(envsWithData.get(env.toUpperCase()), "There's no data for env: " + env.toUpperCase());
        JavaType desiredListType = mapper.getTypeFactory().constructParametricType(ArrayList.class, type);
        return mapper.convertValue(specificEnvData, desiredListType);
    }

    private Cookie fetchUserSessionCookie(User userData) {
        String envUrl = getEnvironmentData().getUrl();
        PostAuthenticateApiRequest authenticateApiRequest = new PostAuthenticateApiRequest(envUrl, "");
        Map<String, String> cookies = authenticateApiRequest
                .setEmail(userData.getEmail())
                .setPassword(userData.getPassword())
                .sendRequest()
                .assertRequestSuccess()
                .getResponseCookies();
        String cookieName = cookies.keySet().stream()
                .filter(name -> name.startsWith("PrestaShop"))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
        return new Cookie(cookieName, cookies.get(cookieName), "automationpractice.com", "/", Date.from(Instant.now().plusSeconds(3600)));
    }

}
