package data;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataHelper {
    private DataHelper() {}

    public static class Registration {
        private Registration() {
        }

        public static RegistrationInfo generateByCard(String locale) {
            Faker faker = new Faker(new Locale("ru"));
            return new RegistrationInfo(
                    faker.address().city(),
                    faker.name().name(),
                    faker.phoneNumber().phoneNumber());
        }
    }
}


