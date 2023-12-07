package pl.example.api.dto.test.data;

import pl.example.api.dto.pet.request.pet.addPet.AddPetRequestDto;
import pl.example.api.dto.pet.request.pet.addPet.Category;
import pl.example.api.dto.pet.request.pet.addPet.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AddPetTestDataGenerator extends TestDataGenerator {
    public AddPetRequestDto generatePetWithRandomData() {
        Category category = new Category();
        category.setId(faker().number().numberBetween(1, 9999));
        category.setName(faker().name().title());

        List<String> photoList = new ArrayList<>();
        photoList.add(faker().internet().url());

        Tag tag = new Tag();
        tag.setId(faker().number().numberBetween(1, 9999));
        tag.setName(faker().name().title());

        List<Tag> tagList = new ArrayList<>();
        tagList.add(tag);

        AddPetRequestDto pet = new AddPetRequestDto();
        pet.setId(faker().number().numberBetween(1, 9999));
        pet.setCategory(category);
        pet.setName(faker().name().firstName());
        pet.setPhotoUrls(photoList);
        pet.setTags(tagList);
        pet.setStatus(getRandoStatus());
        return pet;
    }

    private String getRandoStatus() {
        List<String> statusList = Arrays.asList("available", "pending", "sold");
        Random rand = new Random();
        int randomItem = rand.nextInt(statusList.size());
        String randomStatus = statusList.get(randomItem);
        return randomStatus;
    }
}
