package data;

import com.github.javafaker.Faker;
import model.Address;
import model.Contact;
import model.PhoneNumber;

import java.util.Set;
import java.util.TreeSet;

public class DataSource {

    public static Set<Contact> getContactSet() {
        Set<Contact> contactSet = new TreeSet<>();

        Faker faker = new Faker();
        for (int i = 0; i < 10; i++) {
            contactSet.add(new Contact(faker.name().firstName(),faker.name().lastName(),new PhoneNumber(faker.phoneNumber().phoneNumber(),faker.address().countryCode()),
                    new Address(faker.address().streetName(),faker.address().streetAddressNumber(),faker.address().city(),faker.address().country()),
                            faker.date().birthday()));
        }
    return contactSet;
    }
}
