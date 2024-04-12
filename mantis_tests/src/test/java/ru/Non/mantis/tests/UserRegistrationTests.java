package ru.Non.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.Non.mantis.common.CommonFunctions;
import ru.Non.mantis.model.UserData;

import java.time.Duration;
import java.util.List;


public class UserRegistrationTests extends TestBase {

    private Long userId;
    private String user;
    private String password;

    public static List<String> usernameProvider() {
        return List.of(CommonFunctions.randomString(10));
    }

    public static List<UserData> userProvider() {
        var username = CommonFunctions.randomString(8);
        var email = String.format("%s@localhost", username);
        return List.of(new UserData()
                .withName(username)
                .withPassword("password")
                .withRealName(CommonFunctions.randomString(8))
                .withEmail(email));
    }

//    @ParameterizedTest
//    @MethodSource("usernameProvider")
//    void canRegisterUser(String username) {
//        var email = String.format("%s@localhost", username);
//
//        app.jamesCli().addUser(email, "password");
//        app.session().registerUser(username, email);
//
//        var message = app.mail().recieve(email, "password", Duration.ofSeconds(10)).get(0).content();
//        var url = CommonFunctions.extractUrl(message);
//
//        app.session().verifyUser(username, "password", url);
//        app.http().login(username, "password");
//        Assertions.assertTrue(app.http().isLoggedIn());
//    }

    @ParameterizedTest
    @MethodSource("userProvider")
    void canRegisterUser(UserData user) {
        app.jamesApi().addUser(user.email(), user.password());
        userId = app.rest().createUser(user);

        this.user = user.name();
        this.password = user.password();

        var messages = app.mail().recieve(user.email(), user.password(), Duration.ofSeconds(10));
        var message = messages.get(0).content();
        var url = CommonFunctions.extractUrl(message);

        app.session().verifyUser(user.name(), user.password(), url);
        app.http().login(user.name(), user.password());
        Assertions.assertTrue(app.http().isLoggedIn());
    }

    @AfterEach
    void deleteUser() {
        app.mail().drain(String.format("%s@localhost", user), password);
        app.jamesApi().removeUser(String.format("%s@localhost", user), password);
        app.rest().removeUser(userId);
    }



}
