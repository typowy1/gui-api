package pl.example.api.tests.pet;

import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.example.api.dto.request.pet.addPet.AddPetRequestDto;
import pl.example.api.dto.response.pet.addPet.AddPetResponseDto;
import pl.example.api.dto.response.pet.deletePet.DeletePetResponseDto;
import pl.example.api.dto.test.data.AddPetTestDataGenerator;
import pl.example.api.requests.petController.AddANewPetToTheStoreRequest;
import pl.example.api.requests.petController.DeletesAPetRequest;

import java.util.HashMap;

public class DeletePetDTOTests {
    public static long petId;
    private static Logger logger = LogManager.getLogger(DeletePetDTOTests.class);
    private AddPetResponseDto actualPetResponse;
    private DeletePetResponseDto deletePetResponse;
    private HashMap<String, Object> pathParams;

    @BeforeMethod
    public void addPetBeforeTest() {
        AddPetRequestDto pet = new AddPetTestDataGenerator().generatePetWithRandomData();
        logger.info("Pet to add: " + pet);

        actualPetResponse = AddANewPetToTheStoreRequest.addANewPetToTheStore(pet, 200);
        logger.info("Actual Pet: " + actualPetResponse);

        petId = actualPetResponse.getId();
    }

    @TmsLink("ID-3")
    @Severity(SeverityLevel.BLOCKER)
    @Description("The goal of this test is to delete Pet and check if returned proper response")
    @Test()
    public void deletePetTestWithPetDtoTest() {
        pathParams = new HashMap();
        pathParams.put("petId", petId);

        deletePetResponse = DeletesAPetRequest.deleteAPetDto(200, pathParams);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(deletePetResponse.getCode()).describedAs("Wrong value in response delete Pet: code").isEqualTo(200);
            softly.assertThat(deletePetResponse.getType()).describedAs("Wrong value in response Pet: type").isEqualTo("unknown");
            softly.assertThat(deletePetResponse.getMessage()).describedAs("Wrong value in response Pet: message").isEqualTo(String.valueOf(petId));
        });
    }

    @Issue("DEFECT-5")
    @TmsLink("ID-3")
    @Severity(SeverityLevel.BLOCKER)
    @Description("The goal of this test is to delete Pet and check if returned proper response")
    @Test()
    public void deletePetTestWithPetDtoFailedTest() {
        pathParams = new HashMap();
        pathParams.put("petId", 555);

        deletePetResponse = DeletesAPetRequest.deleteAPetDto(200, pathParams);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(deletePetResponse.getCode()).describedAs("Wrong value in response delete Pet: code").isEqualTo(200);
            softly.assertThat(deletePetResponse.getType()).describedAs("Wrong value in response Pet: type").isEqualTo("unknown");
            softly.assertThat(deletePetResponse.getMessage()).describedAs("Wrong value in response Pet: message").isEqualTo(String.valueOf(petId));
        });
    }

}
