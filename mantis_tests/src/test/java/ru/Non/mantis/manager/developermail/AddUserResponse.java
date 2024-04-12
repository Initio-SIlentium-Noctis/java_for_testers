package ru.Non.mantis.manager.developermail;

import ru.Non.mantis.model.DeveloperMailUser;

public record AddUserResponse(boolean success, Object errors, DeveloperMailUser result) {
}
