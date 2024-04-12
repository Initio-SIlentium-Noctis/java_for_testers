package ru.Non.mantis.model;

import io.swagger.client.model.AccessLevel;

public record UserData(String name, String password, String realname, String email, String accessLevel, boolean isEnabled, boolean isProtected) {

    public UserData() {
        this("", "", "", "", "updater", true, false);
    }

    public UserData withName(String name) {
        return new UserData(name, this.password, this.realname, this.email, this.accessLevel, this.isEnabled, this.isProtected);
    }

    public UserData withPassword(String password) {
        return new UserData(this.name, password, this.realname, this.email, this.accessLevel, this.isEnabled, this.isProtected);
    }

    public UserData withRealName(String realname) {
        return new UserData(this.name, this.password, realname, this.email, this.accessLevel, this.isEnabled, this.isProtected);
    }

    public UserData withEmail(String email) {
        return new UserData(this.name, this.password, this.realname, email, this.accessLevel, this.isEnabled, this.isProtected);
    }

    public UserData withAccessLevel(String accessLevel) {
        return new UserData(this.name, this.password, this.realname, this.email, this.accessLevel, this.isEnabled, this.isProtected);
    }

    public UserData withEnabled(boolean isEnabled) {
        return new UserData(this.name, this.password, this.realname, this.email, this.accessLevel, isEnabled, this.isProtected);
    }

    public UserData withProtected(boolean isProtected) {
        return new UserData(this.name, this.password, this.realname, this.email, this.accessLevel, this.isEnabled, isProtected);
    }


}
