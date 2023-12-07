package pl.example.api.apiHelper;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.example.api.Base;
import pl.example.api.properties.AppProperties;

import java.io.File;

import static io.restassured.RestAssured.given;

public class ApiHelper {

    public static Response post(JSONObject petJsonObject, String endpoint, int statusCode) {
        return given()
                .spec(Base.requestSpecWithLogs()) //powtarzalna specyfikacja dla wszystkich requestów
                .body(petJsonObject.toString())
                .post(AppProperties.getBaseUri() + endpoint)
                .then()
                .statusCode(statusCode)
                .log().ifError() // logowanie jesli error, dostarcza wiecej informacji
                .extract()
                .response();
    }

    public static Response post(File petFile, String endpoint, int statusCode) {
        return given()
                .spec(Base.requestSpecWithLogs()) //powtarzalna specyfikacja dla wszystkich requestów
                .body(petFile)
                .post(AppProperties.getBaseUri() + endpoint)
                .then()
                .statusCode(statusCode)
                .log().ifError() // logowanie jesli error, dostarcza wiecej informacji
                .extract()
                .response();
    }

    // meteoda ykożystuje pojo, deserializacja czyli zamiast typu zwracanego response zwrócimy obiekt javovy CreateTaskResponseDto
    public static Response post(Object object, String endpoint, int statusCode) {
        return given()
                .spec(Base.requestSpecWithLogs()) //powtarzalna specyfikacja dla wszystkich requestów
                .body(object) //przy dto nie musimy wywoływac metody to string, poniewaz juz zawiera
                .post(AppProperties.getBaseUri() + endpoint)
                .then()
                .statusCode(statusCode) //tu zwróćmy status cod bo po deserializacji on nam znicknie i nie bedzie do niego dostępu
                .log().ifError() // logowanie jesli error, dostarcza wiecej informacji
                .extract()
                .response();
//                .as(AddPetResponseDto.class);
    }
}
