package pl.example.api.dto.pet.response.pet.addPet;

import lombok.Data;

import java.util.List;

@Data
public class AddPetResponseDto {

    private Long id;
    private Category category;
    private String name;
    private List<String> photoUrls = null;
    private List<Tag> tags = null;
    private String status;
}
