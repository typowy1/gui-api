package pl.example.api.tests.pet;

import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pl.example.api.dto.request.pet.addPet.AddPetRequestDto;
import pl.example.api.dto.response.pet.addPet.AddPetResponseDto;
import pl.example.api.dto.response.pet.deletePet.DeletePetResponseDto;
import pl.example.api.dto.test.data.AddPetTestDataGenerator;
import pl.example.api.requests.petController.AddANewPetToTheStoreRequest;
import pl.example.api.requests.petController.DeletesAPetRequest;

import java.util.HashMap;

public class AddPetDTOTests {
    private static Logger logger = LogManager.getLogger(AddPetDTOTests.class);
    private AddPetResponseDto actualPetResponse;
    private DeletePetResponseDto deletePetResponse;
    private AddPetRequestDto petDto;

    private HashMap<String, Object> pathParams;

    public static long petId;


    @TmsLink("ID-1")
    @Severity(SeverityLevel.CRITICAL)
    @Description("The goal of this test is to create pet and check if returned Pet object is the same")
    @Test
    public void addPetTestWithPetDtoTest() {
        petDto = new AddPetTestDataGenerator().generatePetWithRandomData();
        logger.info("Pet to add: " + petDto);

        actualPetResponse = AddANewPetToTheStoreRequest.addANewPetToTheStore(petDto, 200);
        logger.info("Actual Pet: " + actualPetResponse);
        petId = actualPetResponse.getId();

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(actualPetResponse).describedAs("actualPetResponse should be equal to pet")
                    .usingRecursiveComparison().ignoringFields("id").isEqualTo(petDto);

            softly.assertThat(actualPetResponse.getId()).describedAs("Wrong value in response Pet: id").isNotNull();
            softly.assertThat(actualPetResponse.getId()).describedAs("Wrong value in response Pet: id").isGreaterThan(0);
        });
    }

    @Issue("DEFECT-2") //link do buga
    @TmsLink("ID-1") // link do scenariusza
    @Severity(SeverityLevel.CRITICAL)// ważnosć scenariusza
    @Description("The goal of this test is to create pet and check if returned Pet object is the same")// opis
    @Test
    public void addPetTestWithPetDtoFailedTest() {
        petDto = new AddPetTestDataGenerator().generatePetWithRandomData();
        logger.info("Pet to add: " + petDto);

        actualPetResponse = AddANewPetToTheStoreRequest.addANewPetToTheStore(petDto, 200);
        logger.info("Actual Pet: " + actualPetResponse);
        petId = actualPetResponse.getId();

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(actualPetResponse).describedAs("actualPetResponse should be equal to pet")
                    .usingRecursiveComparison().ignoringFields("id").isEqualTo(petDto);

            softly.assertThat(actualPetResponse.getId()).describedAs("Wrong value in response Pet: id").isEqualTo(555);
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
