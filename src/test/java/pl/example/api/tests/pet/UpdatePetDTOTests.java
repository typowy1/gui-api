package pl.example.api.tests.pet;

import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.example.api.dto.request.pet.addPet.AddPetRequestDto;
import pl.example.api.dto.request.pet.updatePet.UpdatePetRequestDto;
import pl.example.api.dto.response.pet.UpdatePet.UpdatePetResponseDto;
import pl.example.api.dto.response.pet.addPet.AddPetResponseDto;
import pl.example.api.dto.response.pet.deletePet.DeletePetResponseDto;
import pl.example.api.dto.test.data.AddPetTestDataGenerator;
import pl.example.api.dto.test.data.UpdatePetTestDataGenerator;
import pl.example.api.requests.petController.AddANewPetToTheStoreRequest;
import pl.example.api.requests.petController.DeletesAPetRequest;
import pl.example.api.requests.petController.UpdateAnExistingPetRequest;

import java.util.HashMap;

public class UpdatePetDTOTests {
    public static long petId;
    public static int categoryId;
    public static int tagId;
    private static Logger logger = LogManager.getLogger(UpdatePetDTOTests.class);
    private AddPetResponseDto actualPetResponse;
    private UpdatePetResponseDto actualUpdatedPetResponse;
    private DeletePetResponseDto deletePetResponse;
    private HashMap<String, Object> pathParams;

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

    @TmsLink("ID-4")
    @Severity(SeverityLevel.BLOCKER)
    @Description("The goal of this test is to update Pet and check if returned proper response")
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

    @Issue("DEFECT-6")
    @TmsLink("ID-6")
    @Severity(SeverityLevel.BLOCKER)
    @Description("The goal of this test is to update Pet and check if returned proper response")
    @Test
    public void updatePetTestWithPetDtoFailedTest() {
        UpdatePetRequestDto updatePet = new UpdatePetTestDataGenerator().generateUpdatePetWithRandomData(petId, categoryId, tagId);
        logger.info("updatePet: " + updatePet);

        actualUpdatedPetResponse = UpdateAnExistingPetRequest.updateAnExistingPet(updatePet, 200);
        logger.info("actualUpdatedPetResponse: " + actualUpdatedPetResponse);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(actualUpdatedPetResponse.getName()).describedAs("Wrong value in response Pet: name").isEqualTo("Tom");
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
