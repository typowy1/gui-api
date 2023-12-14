package pl.example.api.requests.petController;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONObject;
import pl.example.api.apiHelper.ApiHelper;
import pl.example.api.dto.request.pet.addPet.AddPetRequestDto;
import pl.example.api.dto.response.pet.addPet.AddPetResponseDto;
import pl.example.api.properties.AppProperties;

import java.io.File;

public class AddANewPetToTheStoreRequest {
    private static final String ADD_PET_ENDPOINT = AppProperties.getKeyFromApiProperty("addPet");
    @Step("Create Pet")
    public static Response addANewPetToTheStore(JSONObject petJsonObject, int statusCode) {
        return ApiHelper.post(petJsonObject, ADD_PET_ENDPOINT, statusCode);
    }

    @Step("Create Pet")
    public static Response addANewPetToTheStore(File filePath, int statusCode) {
        return ApiHelper.post(filePath, ADD_PET_ENDPOINT, statusCode);
    }

    @Step("Create Pet")
    // meteoda wykorzystuje pojo, deserializacja zwrócimy response i wstrzykniemy go do klasy AddPetResponseDto, która reprezentuje response utworzonego Pet
    public static AddPetResponseDto addANewPetToTheStore(AddPetRequestDto petDto, int statusCode) {
        return ApiHelper.post(petDto, ADD_PET_ENDPOINT, statusCode).as(AddPetResponseDto.class);
    }
}
