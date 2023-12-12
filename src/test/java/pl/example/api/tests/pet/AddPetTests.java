package pl.example.api.tests.pet;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pl.example.api.dto.pet.request.pet.addPet.AddPetRequestDto;
import pl.example.api.dto.pet.response.pet.addPet.AddPetResponseDto;
import pl.example.api.dto.pet.response.pet.deletePet.DeletePetResponseDto;
import pl.example.api.dto.test.data.AddPetTestDataGenerator;
import pl.example.api.requests.petController.AddANewPetToTheStoreRequest;
import pl.example.api.requests.petController.DeletesAPetRequest;

import java.util.HashMap;
import java.util.Map;

public class AddPetTests {
    private static Logger logger = LogManager.getLogger(AddPetTests.class);
    private AddPetResponseDto actualPetResponse;
    private DeletePetResponseDto deletePetResponse;
    private Response response;

    private HashMap<String, Object> pathParams;

    public static long petId;

    @Test
    public void addPetTestWithPetDtoTest() {
        AddPetRequestDto pet = new AddPetTestDataGenerator().generatePetWithRandomData();
        logger.info("Pet to add: " + pet);

        actualPetResponse = AddANewPetToTheStoreRequest.addANewPetToTheStore(pet, 200);
        logger.info("Actual Pet: " + actualPetResponse);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(actualPetResponse).describedAs("actualPetResponse should be equal to pet")
                    .usingRecursiveComparison().ignoringFields("id").isEqualTo(pet);

            softly.assertThat(actualPetResponse.getId()).describedAs("Wrong value in response Pet: id").isNotNull();
            softly.assertThat(actualPetResponse.getId()).describedAs("Wrong value in response Pet: id").isGreaterThan(0);
        });
        petId = actualPetResponse.getId();
    }

    @AfterMethod
    public void cleanUpAfterTest() {
        pathParams = new HashMap();
        pathParams.put("petId", petId);

        deletePetResponse = DeletesAPetRequest.deleteAPetDto(200, pathParams);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(deletePetResponse.getCode()).describedAs("Wrong value in response delete Pet: code").isEqualTo(200);
            softly.assertThat(deletePetResponse.getMessage()).describedAs("Wrong value in response Pet: message").isEqualTo(String.valueOf(petId));
        });
    }

}
