package pl.example.api.dto.request.pet.updatePet;

import lombok.Data;

import java.util.List;

@Data
public class UpdatePetRequestDto {
    private Long id;
    private Category category;
    private String name;
    private List<String> photoUrls = null;
    private List<Tag> tags = null;
    private String status;
}
