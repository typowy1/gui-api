package pl.example.api.dto.response.pet.UpdatePet;

import lombok.Data;

import java.util.List;
@Data
public class UpdatePetResponseDto {
    private Long id;
    private Category category;
    private String name;
    private List<String> photoUrls = null;
    private List<Tag> tags = null;
    private String status;
}
