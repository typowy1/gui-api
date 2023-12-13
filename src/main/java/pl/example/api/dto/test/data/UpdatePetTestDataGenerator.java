package pl.example.api.dto.test.data;

import pl.example.api.dto.request.pet.updatePet.Category;
import pl.example.api.dto.request.pet.updatePet.Tag;
import pl.example.api.dto.request.pet.updatePet.UpdatePetRequestDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class UpdatePetTestDataGenerator extends TestDataGenerator{

    public UpdatePetRequestDto generateUpdatePetWithRandomData(Long petId, int categoryId, int tagId) {

        Category category = new Category();
        category.setId(categoryId);
        category.setName(faker().name().title());

        List<String> photoList = new ArrayList<>();
        photoList.add(faker().internet().url());

        Tag tag = new Tag();
        tag.setId(tagId);
        tag.setName(faker().name().title());
        List<Tag> tagList = new ArrayList<>();
        tagList.add(tag);

        UpdatePetRequestDto updatePet = new UpdatePetRequestDto();
        updatePet.setId(petId);
        updatePet.setCategory(category);
        updatePet.setName(faker().name().firstName());
        updatePet.setPhotoUrls(photoList);
        updatePet.setTags(tagList);
        updatePet.setStatus(getRandoStatus());
        return updatePet;
    }
    private String getRandoStatus() {
        List<String> statusList = Arrays.asList("available", "pending", "sold", "available", "pending", "sold", "available", "pending", "sold");
        Random rand = new Random();
        int randomItem = rand.nextInt(statusList.size());
        String randomStatus = statusList.get(randomItem);
        return randomStatus;
    }

}
