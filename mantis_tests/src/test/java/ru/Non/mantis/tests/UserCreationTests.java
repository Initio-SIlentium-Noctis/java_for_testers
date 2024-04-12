package ru.Non.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.Non.mantis.common.CommonFunctions;
import ru.Non.mantis.model.DeveloperMailUser;

import java.time.Duration;

public class UserCreationTests extends TestBase {

    DeveloperMailUser user;

    @Test
    void canRegisterUser() {
        var password = "password";
        user = app.developerMail().addUser();
        var email = String.format("%s@developermail.com", user.name());

        app.session().registerUser(user.name(), email);

        var message = app.developerMail().recieve(user, Duration.ofSeconds(10));
        var url = CommonFunctions.extractUrl(message);

        app.session().verifyUser(user.name(), password, url);
        app.http().login(user.name(), password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }

    @AfterEach
    void deleteMailUser() {
        app.developerMail().deleteUser(user);
    }
}
