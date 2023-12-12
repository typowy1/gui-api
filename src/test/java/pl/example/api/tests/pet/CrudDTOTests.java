package pl.example.api.tests.pet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import pl.example.api.dto.pet.request.pet.addPet.AddPetRequestDto;
import pl.example.api.dto.pet.request.pet.updatePet.UpdatePetRequestDto;
import pl.example.api.dto.pet.response.pet.UpdatePet.UpdatePetResponseDto;
import pl.example.api.dto.pet.response.pet.addPet.AddPetResponseDto;
import pl.example.api.dto.pet.response.pet.deletePet.DeletePetResponseDto;
import pl.example.api.dto.pet.response.pet.findPetById.FindPetByIdResponseDto;
import pl.example.api.dto.test.data.AddPetTestDataGenerator;
import pl.example.api.dto.test.data.UpdatePetTestDataGenerator;
import pl.example.api.requests.petController.AddANewPetToTheStoreRequest;
import pl.example.api.requests.petController.DeletesAPetRequest;
import pl.example.api.requests.petController.FindPetByIdRequest;
import pl.example.api.requests.petController.UpdateAnExistingPetRequest;

import java.util.HashMap;
import java.util.Map;

public class CrudDTOTests {

    public static long petId;
    public static int categoryId;
    public static int tagId;
    private static Logger logger = LogManager.getLogger(FindPetByIdTests.class);
    private AddPetResponseDto actualPetResponse;
    private AddPetRequestDto pet;
    private DeletePetResponseDto deletePetResponse;
    private FindPetByIdResponseDto findPetByIdResponse;
    private UpdatePetResponseDto actualUpdatedPetResponse;
    private HashMap<String, Object> pathParams;

    @Test(priority = 1)
    public void addPetTestWithPetDtoTest() {
        pet = new AddPetTestDataGenerator().generatePetWithRandomData();
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

    @Test(priority = 2)
    public void findPetByIdDto() {
        pathParams = new HashMap();
        pathParams.put("petId", petId);

        findPetByIdResponse = FindPetByIdRequest.findPetByIdDto(200, pathParams);
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(findPetByIdResponse).describedAs("findPetByIdResponse should be equal to pet")
                    .usingRecursiveComparison().ignoringFields("id").isEqualTo(pet);

            softly.assertThat(findPetByIdResponse.getId()).describedAs("Wrong value in response Pet: id").isEqualTo(petId);
        });
    }

    @Test(priority = 3)
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

    @Test(priority = 4)
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
}
