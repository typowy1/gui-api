package pl.example.api.requests.petController;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import pl.example.api.apiHelper.ApiHelper;
import pl.example.api.dto.response.pet.deletePet.DeletePetResponseDto;
import pl.example.api.properties.AppProperties;

import java.util.Map;

public class DeletesAPetRequest {

    private static final String DELETE_PET_ENDPOINT = AppProperties.getKeyFromApiProperty("deletePet");

    @Step("Delete Pet")
    public static DeletePetResponseDto deleteAPetDto(int statusCode, Map pathParams) {
        return ApiHelper.delete(DELETE_PET_ENDPOINT, statusCode, pathParams).as(DeletePetResponseDto.class);
    }

    @Step("Delete Pet")
    public static Response deleteAPet(int statusCode, Map pathParams) {
        return ApiHelper.delete(DELETE_PET_ENDPOINT, statusCode, pathParams);
    }
}
