package pl.example.api.requests.petController;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONObject;
import pl.example.api.apiHelper.ApiHelper;
import pl.example.api.dto.request.pet.updatePet.UpdatePetRequestDto;
import pl.example.api.dto.response.pet.UpdatePet.UpdatePetResponseDto;
import pl.example.api.properties.AppProperties;

import java.io.File;

public class UpdateAnExistingPetRequest {

    private static final String ADD_PET_ENDPOINT = AppProperties.getKeyFromApiProperty("updatePet");

    @Step("Update Pet")
    public static Response updateAnExistingPet(JSONObject petJsonObject, int statusCode) {
        return ApiHelper.put(petJsonObject, ADD_PET_ENDPOINT, statusCode);
    }

    @Step("Update Pet")
    public static Response updateAnExistingPet(File filePath, int statusCode) {
        return ApiHelper.put(filePath, ADD_PET_ENDPOINT, statusCode);
    }

    // meteoda wykorzystuje pojo, deserializacja zwrócimy response i wstrzykniemy go do klasy AddPetResponseDto, która reprezentuje response utworzonego Pet
    @Step("Update Pet")
    public static UpdatePetResponseDto updateAnExistingPet(UpdatePetRequestDto updatePetDto, int statusCode) {
        return ApiHelper.put(updatePetDto, ADD_PET_ENDPOINT, statusCode).as(UpdatePetResponseDto.class);
    }
}
