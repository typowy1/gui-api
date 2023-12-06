package pl.example.api.dto.test.data;

import com.github.javafaker.Faker;

public class TestDataGenerator {
    public Faker faker() {
        return Faker.instance();
    }
}
