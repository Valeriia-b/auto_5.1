package data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class DataHelper {
    private DataHelper() {}

    public static class Registration {
        private Registration() {
        }

        public static RegistrationInfo generateByCard(String locale) {
            Faker faker = new Faker(new Locale("ru"));
            return new RegistrationInfo(
                    faker.name().name(),
                    faker.phoneNumber().phoneNumber());
        }
        public static String deliveryDay (int days){
            return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
           }

        public static String getCity() {
            List<String> list = Arrays.asList(
                    "Москва", "Санкт-Петербург", "Архангельск", "Симферополь", "Краснодар", "Казань");
            Random item = new Random();
            return list.get(item.nextInt(list.size()));
        }
    }
}


