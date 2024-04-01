package ru.Non.mantis.tests;

import org.junit.jupiter.api.Test;
import ru.Non.mantis.common.CommonFunctions;

public class JamesTests extends TestBase {

    @Test
    void canCreateUser() {
        app.jamesCli().addUser(String.format("%s@localhost", CommonFunctions.randomString(8)), "password");
    }

    @Test
    void canRemoveUser() {
        app.jamesCli().removeUser(String.format("%s@localhost", "user1"));
    }

}
