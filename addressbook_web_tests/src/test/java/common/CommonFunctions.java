package common;

import tests.TestBase;

import java.io.File;
import java.nio.file.Paths;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonFunctions {
    public static String randomString(int n) {
        var rnd = new Random();
        Supplier<Integer> randomNumbers = () -> rnd.nextInt(26);
        var result = Stream.generate(randomNumbers)
                .limit(n)
                .map(i -> 'a' + i)
                .map(Character::toString)
                .collect(Collectors.joining());
        return result;
    }

    public static String randomFirstName() {
        String[] names = {"Владимир", "Сергей", "Дмитрий", "Никита", "Илья", "Иван", "Кирилл", "Егор", "Артем", "Андрей"};
        String result = names[TestBase.randomNumber(names.length)];
        return result;
    }

    public static String randomLastName() {
        String[] lastnames = {"Петров", "Сидоров", "Макаров", "Калашников", "Смирнов", "Михайлов", "Фёдоров", "Иванов", "Михайлов", "Яковлев"};
        String result = lastnames[TestBase.randomNumber(lastnames.length)];
        return result;
    }

    public static String randomAddress() {
        String[] citynames = {"Москва", "Санкт-Петербург", "Тверь", "Астрахань", "Пермь", "Петрозаводск", "Красногорск", "Уфа", "Челябинск", "Краснодар"};
        String[] streetnames = {"Цветочная", "Весенняя", "Тенистая", "Садовая", "Луговая", "Берёзовая", "Абрикосовая", "Виноградная", "Солнечная", "Летняя"};
        String result = "г. ";
        result = result + citynames[TestBase.randomNumber(citynames.length)] + ", ул. " + streetnames [TestBase.randomNumber(streetnames.length)] + " , д. " + (1 + TestBase.randomNumber(24));
        return result;
    }

    public static String randomEmail() {
        String[] emails = {"johnny_dazzle", "rainbowunicorn", "spacepirate", "quantumlemonade", "neon_dragonfly", "starship_explorer", "holographic_artist", "cybernetic_squirrel", "binary_chef", "virtual_gardener"};
        String[] domains = {"gmail.com", "mail.ru", "msn.com", "hotmail.com", "aol.com", "yahoo.com", "europe.com", "rambler.ru", "vk.ru", "yandex.ru"};
        String result = emails[TestBase.randomNumber(emails.length)] + TestBase.randomNumber(100) + "@" + domains[TestBase.randomNumber(domains.length)];
        return result;
    }

    public static String randomPhone() {
        String[] prefix = {"916", "915", "926", "910", "911", "925"};
        String result = "+7" + prefix[TestBase.randomNumber(prefix.length)];
        for (int i = 0; i < 7; i++) {
            result = result + TestBase.randomNumber(10);
        }
        return result;
    }

    public static String randomFile(String dir) {
        var filenames = new File(dir).list();
        var rnd = new Random();
        var index = rnd.nextInt(filenames.length);
        return Paths.get(dir, filenames[index]).toString();
    }
}
