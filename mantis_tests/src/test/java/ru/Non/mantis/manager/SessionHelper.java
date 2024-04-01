package ru.Non.mantis.manager;

import org.openqa.selenium.By;

public class SessionHelper extends HelperBase {

    public SessionHelper(ApplicationManager manager) {
        super(manager);
    }


    public void login(String user, String password) {
        type(By.name("username"), user);
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"), password);
        click(By.cssSelector("input[type='submit']"));
    }

    public boolean isLoggedIn() {
        return isElementPresent(By.cssSelector("span.user-info"));
    }

    public void register(String user, String email) {
        click(By.xpath("//a[contains(text(), 'Signup for a new account')]"));
        type(By.id("username"), user);
        type(By.id("email-field"), email);
        click(By.cssSelector("input[type='submit']"));
        click(By.xpath("//a[contains(text(), 'Proceed')]"));
    }

    public void verify(String user, String password, String url) {
        openUrl(url);
        type(By.id("realname"), user);
        type(By.id("password"), password);
        type(By.id("password-confirm"), password);
        click(By.cssSelector("button[type='submit']"));
        openHomePage();
    }
}
