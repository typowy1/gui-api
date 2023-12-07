package pl.example.api.requests.petController;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.example.api.apiHelper.ApiHelper;
import pl.example.api.dto.pet.request.pet.addPet.AddPetRequestDto;
import pl.example.api.dto.pet.response.pet.addPet.AddPetResponseDto;
import pl.example.api.properties.AppProperties;

import java.io.File;

public class AddANewPetToTheStoreRequest {
    private static final String PET_ENDPOINT = AppProperties.getKeyFromApiProperty("addPet");

    public static Response addANewPetToTheStore(JSONObject petJsonObject, int statusCode) {
        return ApiHelper.post(petJsonObject, PET_ENDPOINT, statusCode);
    }

    public static Response addANewPetToTheStore(File petFile, int statusCode) {
        return ApiHelper.post(petFile, PET_ENDPOINT, statusCode);
    }

    // meteoda wykorzystuje pojo, deserializacja zwrócimy response i wstrzykniemy go do klasy AddPetResponseDto, która reprezentuje response utworzonego Pet
    public static AddPetResponseDto addANewPetToTheStore(AddPetRequestDto petDto, int statusCode) {
        return ApiHelper.post(petDto, PET_ENDPOINT, statusCode).as(AddPetResponseDto.class);
    }
}
