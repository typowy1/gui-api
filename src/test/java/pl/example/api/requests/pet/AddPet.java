package pl.example.api.requests.pet;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.example.api.dto.pet.response.AddPetResponseDto;
import pl.example.api.properties.AppProperties;
import pl.example.api.requests.BaseRequest;

import static io.restassured.RestAssured.given;

public class AddPet {
    private static final String ADD_PET_ENDPOINT = AppProperties.getKeyFromApiProperty("addPet");

    public static Response addPet(JSONObject body) {
        return given()
                .spec(BaseRequest.requestSpecWithLogs()) //powtarzalna specyfikacja dla wszystkich requestów
                .body(body.toString())
                .post(AppProperties.getBaseUri() + ADD_PET_ENDPOINT)
                .then()
                .log().ifError() // logowanie jesli error, dostarcza wiecej informacji
                .extract()
                .response();
    }

    // meteoda ykożystuje pojo, deserializacja czyli zamiast typu zwracanego response zwrócimy obiekt javovy CreateTaskResponseDto
    public static AddPetResponseDto addPet(AddPetResponseDto pet) {
        return given()
                .spec(BaseRequest.requestSpecWithLogs()) //powtarzalna specyfikacja dla wszystkich requestów
                .body(pet) //przy dto nie musimy wywoływac metody to string, poniewaz juz zawiera
                .post(AppProperties.getBaseUri() + ADD_PET_ENDPOINT)
                .then()
                .statusCode(200) //tu zwróćmy status cod bo po deserializacji on nam znicknie i nie bedzie do niego dostępu
                .log().ifError() // logowanie jesli error, dostarcza wiecej informacji
                .extract()
                .response()
                .as(AddPetResponseDto.class);
    }
}
