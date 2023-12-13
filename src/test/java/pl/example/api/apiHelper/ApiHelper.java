package pl.example.api.apiHelper;

import io.restassured.response.Response;
import org.json.JSONObject;
import pl.example.api.Base;
import pl.example.api.properties.AppProperties;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiHelper {

//    POST
    public static Response post(JSONObject jsonObject, String endpoint, int statusCode) {
        return given()
                .spec(Base.requestSpecWithLogs()) //powtarzalna specyfikacja dla wszystkich requestów
                .body(jsonObject.toString())
                .post(AppProperties.getBaseUri() + endpoint)
                .then()
                .statusCode(statusCode)
                .log().ifError() // logowanie jesli error, dostarcza wiecej informacji
                .extract()
                .response();
    }

    public static Response post(File filePath, String endpoint, int statusCode) {
        return given()
                .spec(Base.requestSpecWithLogs()) //powtarzalna specyfikacja dla wszystkich requestów
                .body(filePath)
                .post(AppProperties.getBaseUri() + endpoint)
                .then()
                .statusCode(statusCode)
                .log().ifError() // logowanie jesli error, dostarcza wiecej informacji
                .extract()
                .response();
    }

    // meteoda wykorzystuje pojo, deserializacja czyli zamiast typu zwracanego response zwrócimy obiekt javovy CreateTaskResponseDto
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

//    PUT
    public static Response put(JSONObject jsonObject, String endpoint, int statusCode) {
        return given()
                .spec(Base.requestSpecWithLogs()) //powtarzalna specyfikacja dla wszystkich requestów
                .body(jsonObject.toString())
                .put(AppProperties.getBaseUri() + endpoint)
                .then()
                .statusCode(statusCode)
                .log().ifError() // logowanie jesli error, dostarcza wiecej informacji
                .extract()
                .response();
    }

    public static Response put(File filePath, String endpoint, int statusCode) {
        return given()
                .spec(Base.requestSpecWithLogs()) //powtarzalna specyfikacja dla wszystkich requestów
                .body(filePath)
                .put(AppProperties.getBaseUri() + endpoint)
                .then()
                .statusCode(statusCode)
                .log().ifError() // logowanie jesli error, dostarcza wiecej informacji
                .extract()
                .response();
    }

    // meteoda wykorzystuje pojo, deserializacja czyli zamiast typu zwracanego response zwrócimy obiekt javovy CreateTaskResponseDto
    public static Response put(Object object, String endpoint, int statusCode) {
        return given()
                .spec(Base.requestSpecWithLogs()) //powtarzalna specyfikacja dla wszystkich requestów
                .body(object) //przy dto nie musimy wywoływac metody to string, poniewaz juz zawiera
                .put(AppProperties.getBaseUri() + endpoint)
                .then()
                .statusCode(statusCode) //tu zwróćmy status cod bo po deserializacji on nam znicknie i nie bedzie do niego dostępu
                .log().ifError() // logowanie jesli error, dostarcza wiecej informacji
                .extract()
                .response();
//                .as(AddPetResponseDto.class);
    }


//    GET
    public static Response get(String endpoint, int statusCode, Map pathParams) {
        return given()
                .spec(Base.requestSpecWithLogs()) //powtarzalna specyfikacja dla wszystkich requestów
                .pathParams(pathParams)
                .get(AppProperties.getBaseUri() + endpoint)
                .then()
                .statusCode(statusCode)
                .log().ifError() // logowanie jesli error, dostarcza wiecej informacji
                .extract()
                .response();
    }


//    DELETE
    public static Response delete(String endpoint, int statusCode, Map pathParams) {
        return given()
                .spec(Base.requestSpecWithLogs()) //powtarzalna specyfikacja dla wszystkich requestów
                .pathParams(pathParams)
                .delete(AppProperties.getBaseUri() + endpoint)
                .then()
                .statusCode(statusCode)
                .log().ifError() // logowanie jesli error, dostarcza wiecej informacji
                .extract()
                .response();
    }
}
