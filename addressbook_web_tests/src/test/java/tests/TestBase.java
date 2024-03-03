package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;

import java.util.Random;

public class TestBase {

    protected static ApplicationManager app;

    @BeforeEach
    public void setUp() {
        if (app == null) {
            app = new ApplicationManager();
            app.init(System.getProperty("browser", "firefox"));
        }
    }

    public static String randomString(int n) {
        var rnd = new Random();
        var result = "";
        for (int i = 0; i < n; i++) {
            result = result + (char)('a' + rnd.nextInt(26));
        }
        return result;
    }

    public static int randomNumber(int range) {
        var rnd = new Random();
        return rnd.nextInt(range);
    }

    public static String randomFirstName() {
        String[] names = {"Владимир", "Сергей", "Дмитрий", "Никита", "Илья", "Иван", "Кирилл", "Егор", "Артем", "Андрей"};
        String result = names[randomNumber(names.length)];
        return result;
    }

    public static String randomLastName() {
        String[] lastnames = {"Петров", "Сидоров", "Макаров", "Калашников", "Смирнов", "Михайлов", "Фёдоров", "Иванов", "Михайлов", "Яковлев"};
        String result = lastnames[randomNumber(lastnames.length)];
        return result;
    }

    public static String randomAddress() {
        String[] citynames = {"Москва", "Санкт-Петербург", "Тверь", "Астрахань", "Пермь", "Петрозаводск", "Красногорск", "Уфа", "Челябинск", "Краснодар"};
        String[] streetnames = {"Цветочная", "Весенняя", "Тенистая", "Садовая", "Луговая", "Берёзовая", "Абрикосовая", "Виноградная", "Солнечная", "Летняя"};
        String result = "г. ";
        result = result + citynames[randomNumber(citynames.length)] + ", ул. " + streetnames [randomNumber(streetnames.length)] + " , д. " + (1 + randomNumber(24));
        return result;
    }

    public static String randomEmail() {
        String[] emails = {"johnny_dazzle", "rainbowunicorn", "spacepirate", "quantumlemonade", "neon_dragonfly", "starship_explorer", "holographic_artist", "cybernetic_squirrel", "binary_chef", "virtual_gardener"};
        String[] domains = {"gmail.com", "mail.ru", "msn.com", "hotmail.com", "aol.com", "yahoo.com", "europe.com", "rambler.ru", "vk.ru", "yandex.ru"};
        String result = emails[randomNumber(emails.length)] + randomNumber(100) + "@" + domains[randomNumber(domains.length)];
        return result;
    }

    public static String randomPhone() {
        String[] prefix = {"916", "915", "926", "910", "911", "925"};
        String result = "+7" + prefix[randomNumber(prefix.length)];
        for (int i = 0; i < 7; i++) {
            result = result + randomNumber(10);
        }
        return result;
    }

}
