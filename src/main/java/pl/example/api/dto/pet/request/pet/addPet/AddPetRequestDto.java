package pl.example.api.dto.pet.request.pet.addPet;

import lombok.Data;

import java.util.List;

@Data
public class AddPetRequestDto {
    private Category category;
    private String name;
    private List<String> photoUrls = null;
    private List<Tag> tags = null;
    private String status;
}
