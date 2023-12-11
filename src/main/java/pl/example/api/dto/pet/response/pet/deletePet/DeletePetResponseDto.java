package pl.example.api.dto.pet.response.pet.deletePet;

import lombok.Data;

@Data
public class DeletePetResponseDto {
    private Integer code;
    private String type;
    private String message;
}
