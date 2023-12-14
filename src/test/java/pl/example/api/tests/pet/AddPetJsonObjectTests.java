package pl.example.api.tests.pet;

import com.github.javafaker.Faker;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pl.example.api.requests.petController.AddANewPetToTheStoreRequest;
import pl.example.api.requests.petController.DeletesAPetRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddPetJsonObjectTests {

    private static Logger logger = LogManager.getLogger(AddPetDTOTests.class);
    private Faker faker;
    private Response response;
    private JSONObject petJsonObject;

    private HashMap<String, Object> pathParams;

    public static long petId;

    @TmsLink("ID-1")
    @Severity(SeverityLevel.CRITICAL)
    @Description("The goal of this test is to create JSONObject pet and check if returned Pet is the same")
    @Test
    public void addPetTestWithPetJsonObjectTest() {
        faker = new Faker();

        JSONObject category = new JSONObject();
        category.put("id", faker.number().numberBetween(1, 9999));
        category.put("name", faker.name().title());

        List<String> photoUrls = new ArrayList<>();
        photoUrls.add(faker.internet().url());

        JSONObject tag = new JSONObject();
        tag.put("id", faker.number().numberBetween(1, 9999));
        tag.put("name", faker.name().title());

        List<JSONObject> tags = new ArrayList<>();
        tags.add(tag);

        petJsonObject = new JSONObject();
        petJsonObject.put("category", category);
        petJsonObject.put("name", faker.name().firstName());
        petJsonObject.put("photoUrls", photoUrls);
        petJsonObject.put("tags", tags);
        petJsonObject.put("status", "available");

        logger.info("Pet to add: " + petJsonObject);

        response = AddANewPetToTheStoreRequest.addANewPetToTheStore(petJsonObject, 200);
        petId = response.jsonPath().getLong("id");

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.jsonPath().getLong("id")).describedAs("Wrong value in response, id").isNotNull();
            softly.assertThat(response.jsonPath().getLong("id")).describedAs("Wrong value in response, id").isGreaterThan(0);
            softly.assertThat(response.jsonPath().getInt("category.id")).describedAs("Wrong value in response, category: id").isEqualTo(petJsonObject.getJSONObject("category").getInt("id"));
            softly.assertThat(response.jsonPath().getString("category.name")).describedAs("Wrong value in response, category: name").isEqualTo(petJsonObject.getJSONObject("category").getString("name"));
            softly.assertThat(response.jsonPath().getString("name")).describedAs("Wrong value in response, name").isEqualTo(petJsonObject.getString("name"));
            softly.assertThat(response.jsonPath().getString("photoUrls[0]")).describedAs("Wrong value in response, photoUrls.[0]").isEqualTo(petJsonObject.getJSONArray("photoUrls").get(0));
            softly.assertThat(response.jsonPath().getInt("tags[0].id")).describedAs("Wrong value in response, tags[0].id").isEqualTo(petJsonObject.getJSONArray("tags").getJSONObject(0).getInt("id"));
            softly.assertThat(response.jsonPath().getString("tags[0].name")).describedAs("Wrong value in response, tags[0].id").isEqualTo(petJsonObject.getJSONArray("tags").getJSONObject(0).getString("name"));
            softly.assertThat(response.jsonPath().getString("status")).describedAs("Wrong value in response, status").isEqualTo(petJsonObject.getString("status"));
        });
    }

    @Issue("DEFECT-4")
    @TmsLink("ID-1")
    @Severity(SeverityLevel.CRITICAL)
    @Description("The goal of this test is to create JSONObject pet and check if returned Pet is the same")
    @Test
    public void addPetTestWithPetJsonObjectFailedTest() {
        faker = new Faker();

        JSONObject category = new JSONObject();
        category.put("id", faker.number().numberBetween(1, 9999));
        category.put("name", faker.name().title());

        List<String> photoUrls = new ArrayList<>();
        photoUrls.add(faker.internet().url());

        JSONObject tag = new JSONObject();
        tag.put("id", faker.number().numberBetween(1, 9999));
        tag.put("name", faker.name().title());

        List<JSONObject> tags = new ArrayList<>();
        tags.add(tag);

        petJsonObject = new JSONObject();
        petJsonObject.put("category", category);
        petJsonObject.put("name", faker.name().firstName());
        petJsonObject.put("photoUrls", photoUrls);
        petJsonObject.put("tags", tags);
        petJsonObject.put("status", "available");

        logger.info("Pet to add: " + petJsonObject);

        response = AddANewPetToTheStoreRequest.addANewPetToTheStore(petJsonObject, 200);
        petId = response.jsonPath().getLong("id");

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(response.jsonPath().getLong("id")).describedAs("Wrong value in response, id").isNotNull();
            softly.assertThat(response.jsonPath().getLong("id")).describedAs("Wrong value in response, id").isGreaterThan(0);
            softly.assertThat(response.jsonPath().getInt("category.id")).describedAs("Wrong value in response, category: id").isEqualTo(petJsonObject.getJSONObject("category").getInt("id"));
            softly.assertThat(response.jsonPath().getString("category.name")).describedAs("Wrong value in response, category: name").isEqualTo(petJsonObject.getJSONObject("category").getString("name"));
            softly.assertThat(response.jsonPath().getString("name")).describedAs("Wrong value in response, name").isEqualTo(petJsonObject.getString("name"));
            softly.assertThat(response.jsonPath().getString("photoUrls[0]")).describedAs("Wrong value in response, photoUrls.[0]").isEqualTo(petJsonObject.getJSONArray("photoUrls").get(0));
            softly.assertThat(response.jsonPath().getInt("tags[0].id")).describedAs("Wrong value in response, tags[0].id").isEqualTo(petJsonObject.getJSONArray("tags").getJSONObject(0).getInt("id"));
            softly.assertThat(response.jsonPath().getString("tags[0].name")).describedAs("Wrong value in response, tags[0].id").isEqualTo(petJsonObject.getJSONArray("tags").getJSONObject(0).getString("name"));
            softly.assertThat(response.jsonPath().getString("status")).describedAs("Wrong value in response, status").isEqualTo(petJsonObject.getString("status"));
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
