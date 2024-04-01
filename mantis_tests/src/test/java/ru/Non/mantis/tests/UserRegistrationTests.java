package ru.Non.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.Non.mantis.common.CommonFunctions;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;


public class UserRegistrationTests extends TestBase {

    public static List<String> usernameProvider() {
        return List.of(CommonFunctions.randomString(10));
    }

    @ParameterizedTest
    @MethodSource("usernameProvider")
    void canRegisterUser(String username) {
        var email = String.format("%s@localhost", username);
        String url;

        app.jamesCli().addUser(email, "password");
        app.session().register(username, email);

        var messages = app.mail().recieve(email, "password", Duration.ofSeconds(10));
        var content = messages.get(0).content();

        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(content);

        if (matcher.find()) {
            url = content.substring(matcher.start(), matcher.end());
        } else {
            throw new IllegalArgumentException("Could not extract url from mail");
        }

        app.session().verify(username, "password", url);
        app.http().login(username, "password");
        Assertions.assertTrue(app.http().isLoggedIn());
    }




}
