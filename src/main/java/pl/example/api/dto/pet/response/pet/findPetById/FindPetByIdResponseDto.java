package pl.example.api.dto.pet.response.pet.findPetById;

import lombok.Data;

import java.util.List;

@Data
public class FindPetByIdResponseDto {

    private Long id;
    private Category category;
    private String name;
    private List<String> photoUrls = null;
    private List<Tag> tags = null;
    private String status;

}
