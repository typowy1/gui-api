package pl.example.api.tests.pet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.example.api.dto.pet.request.pet.addPet.AddPetRequestDto;
import pl.example.api.dto.pet.request.pet.updatePet.UpdatePetRequestDto;
import pl.example.api.dto.pet.response.pet.UpdatePet.UpdatePetResponseDto;
import pl.example.api.dto.pet.response.pet.addPet.AddPetResponseDto;
import pl.example.api.dto.pet.response.pet.deletePet.DeletePetResponseDto;
import pl.example.api.dto.test.data.AddPetTestDataGenerator;
import pl.example.api.dto.test.data.UpdatePetTestDataGenerator;
import pl.example.api.requests.petController.AddANewPetToTheStoreRequest;
import pl.example.api.requests.petController.DeletesAPetRequest;
import pl.example.api.requests.petController.UpdateAnExistingPetRequest;

import java.util.HashMap;
import java.util.Map;

public class UpdatePetTests {
    public static long petId;
    public static int categoryId;
    public static int tagId;
    private static Logger logger = LogManager.getLogger(AddPetTests.class);
    private AddPetResponseDto actualPetResponse;
    private UpdatePetResponseDto actualUpdatedPetResponse;
    private DeletePetResponseDto deletePetResponse;
    private Map pathParams;
    @BeforeMethod
    public void addPetBeforeTest() {
        AddPetRequestDto pet = new AddPetTestDataGenerator().generatePetWithRandomData();
        logger.info("Pet to add: " + pet);

        actualPetResponse = AddANewPetToTheStoreRequest.addANewPetToTheStore(pet, 200);
        logger.info("Actual Pet: " + actualPetResponse);

        petId = actualPetResponse.getId();
        categoryId = actualPetResponse.getCategory().getId();
        tagId = actualPetResponse.getTags().get(0).getId();
    }

    @Test
    public void updatePetTestWithPetDtoTest() {
        UpdatePetRequestDto updatePet = new UpdatePetTestDataGenerator().generateUpdatePetWithRandomData(petId, categoryId, tagId);
        logger.info("updatePet: " + updatePet);

        actualUpdatedPetResponse = UpdateAnExistingPetRequest.updateAnExistingPet(updatePet, 200);
        logger.info("actualUpdatedPetResponse: " + actualUpdatedPetResponse);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(actualUpdatedPetResponse).describedAs("Response actualUpdatedPetResponse should be equal to updatePet")
                    .usingRecursiveComparison().isEqualTo(updatePet);
        });
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
