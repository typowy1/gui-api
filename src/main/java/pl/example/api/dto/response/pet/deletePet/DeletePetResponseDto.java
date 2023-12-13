package pl.example.api.dto.response.pet.deletePet;

import lombok.Data;

@Data
public class DeletePetResponseDto {
    private Integer code;
    private String type;
    private String message;
}
