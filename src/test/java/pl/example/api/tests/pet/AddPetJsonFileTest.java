package pl.example.api.tests.pet;

import fileConfig.FileConfiguration;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pl.example.api.properties.AppProperties;
import pl.example.api.requests.petController.AddANewPetToTheStoreRequest;
import pl.example.api.requests.petController.DeletesAPetRequest;

import java.io.File;
import java.util.HashMap;

public class AddPetJsonFileTest {
    private static Logger logger = LogManager.getLogger(AddPetDTOTests.class);
    private Response response;
    private File petJsonFile;

    private static final String ADD_PET_JSON_FILE = AppProperties.getKeyFromApiProperty("addPetFile");

    private HashMap<String, Object> pathParams;

    public static long petId;

    @TmsLink("ID-1")
    @Severity(SeverityLevel.CRITICAL)
    @Description("The goal of this test is to create pet from file and check if returned Pet is the same")
    @Test
    public void addPetTestWithPetJsonFileTest() {
        petJsonFile = FileConfiguration.getFileFomPath(ADD_PET_JSON_FILE);
        logger.info("Pet to add: " + FileConfiguration.readFileAsString(petJsonFile));
//        Jeśli chcemy dynamicznie dodawać wartości do pól, trzba będzie zamienić plikJson na JSONObject i użyć metody put, jsonObject.put("name", "Jacek");

        response = AddANewPetToTheStoreRequest.addANewPetToTheStore(petJsonFile, 200);
        petId = response.jsonPath().getLong("id");

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.jsonPath().getLong("id")).describedAs("Wrong value in response, id").isNotNull();
            softly.assertThat(response.jsonPath().getLong("id")).describedAs("Wrong value in response, id").isGreaterThan(0);
            softly.assertThat(response.jsonPath().getInt("category.id")).describedAs("Wrong value in response, category: id").isGreaterThan(0);
            softly.assertThat(response.jsonPath().getString("category.name")).describedAs("Wrong value in response, category: name").isEqualTo("Dynamic Functionality Technician");
            softly.assertThat(response.jsonPath().getString("name")).describedAs("Wrong value in response, name").isEqualTo("Casey");
            softly.assertThat(response.jsonPath().getString("photoUrls[0]")).describedAs("Wrong value in response, photoUrls.[0]").isEqualTo("www.george-rippin.info");
            softly.assertThat(response.jsonPath().getInt("tags[0].id")).describedAs("Wrong value in response, tags[0].id").isGreaterThan(0);
            softly.assertThat(response.jsonPath().getString("tags[0].name")).describedAs("Wrong value in response, tags[0].id").isEqualTo("District Integration Administrator");
            softly.assertThat(response.jsonPath().getString("status")).describedAs("Wrong value in response, status").isEqualTo("available");
        });
    }

    @Issue("DEFECT-3")
    @TmsLink("ID-1")
    @Severity(SeverityLevel.CRITICAL)
    @Description("The goal of this test is to create pet from file and check if returned Pet is the same")
    @Test
    public void addPetTestWithPetJsonFileFailedTest() {
        petJsonFile = FileConfiguration.getFileFomPath(ADD_PET_JSON_FILE);
        logger.info("Pet to add: " + FileConfiguration.readFileAsString(petJsonFile));
//        Jeśli chcemy dynamicznie dodawać wartości do pól, trzeba będzie zamienić plikJson na JSONObject i użyć metody put ktora nadpisze wartosci, jsonObject.put("name", "Jacek");

        response = AddANewPetToTheStoreRequest.addANewPetToTheStore(petJsonFile, 200);
        petId = response.jsonPath().getLong("id");

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.jsonPath().getLong("id")).describedAs("Wrong value in response, id").isNotNull();
            softly.assertThat(response.jsonPath().getLong("id")).describedAs("Wrong value in response, id").isGreaterThan(0);
            softly.assertThat(response.jsonPath().getInt("category.id")).describedAs("Wrong value in response, category: id").isGreaterThan(0);
            softly.assertThat(response.jsonPath().getString("category.name")).describedAs("Wrong value in response, category: name").isEqualTo("Dynamic Functionality Technician");
            softly.assertThat(response.jsonPath().getString("name")).describedAs("Wrong value in response, name").isEqualTo("Tom");
            softly.assertThat(response.jsonPath().getString("photoUrls[0]")).describedAs("Wrong value in response, photoUrls.[0]").isEqualTo("www.george-rippin.info");
            softly.assertThat(response.jsonPath().getInt("tags[0].id")).describedAs("Wrong value in response, tags[0].id").isGreaterThan(0);
            softly.assertThat(response.jsonPath().getString("tags[0].name")).describedAs("Wrong value in response, tags[0].id").isEqualTo("District Integration Administrator");
            softly.assertThat(response.jsonPath().getString("status")).describedAs("Wrong value in response, status").isEqualTo("available");
        });
    }

    @AfterMethod
    public void cleanUpAfterTest() {
        pathParams = new HashMap();
        pathParams.put("petId", petId);

        response = DeletesAPetRequest.deleteAPet(200, pathParams);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.jsonPath().getInt("code")).describedAs("Wrong value in response, code").isEqualTo(200);
            softly.assertThat(response.jsonPath().getString("type")).describedAs("Wrong value in response, unknown").isEqualTo("unknown");
            softly.assertThat(response.jsonPath().getString("message")).describedAs("Wrong value in response, message").isEqualTo(String.valueOf(petId));

        });
    }


}
