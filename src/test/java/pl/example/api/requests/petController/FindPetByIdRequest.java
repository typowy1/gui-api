package pl.example.api.requests.petController;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import pl.example.api.apiHelper.ApiHelper;
import pl.example.api.dto.response.pet.findPetById.FindPetByIdResponseDto;
import pl.example.api.properties.AppProperties;

import java.util.Map;

public class FindPetByIdRequest {

    private static final String FIND_PET_BY_ID_ENDPOINT = AppProperties.getKeyFromApiProperty("findPetByID");

    @Step("Get Pet")
    public static FindPetByIdResponseDto findPetByIdDto(int statusCode, Map pathParams) {
        return ApiHelper.get(FIND_PET_BY_ID_ENDPOINT, statusCode, pathParams).as(FindPetByIdResponseDto.class);
    }

    @Step("Get Pet")
    public static Response findPetById(int statusCode, Map pathParams) {
        return ApiHelper.get(FIND_PET_BY_ID_ENDPOINT, statusCode, pathParams);
    }
}
